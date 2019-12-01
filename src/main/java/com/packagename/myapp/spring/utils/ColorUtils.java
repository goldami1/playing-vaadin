package com.packagename.myapp.spring.utils;

import java.util.Optional;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class ColorUtils
{
	private ColorUtils()
	{
		
	}
	
	public static RGBColorModel darkenColor(RGBColorModel rgbColor ,int percent)
	{
		float opacity = Optional.ofNullable(rgbColor.getAlpha()).orElseGet(() -> 1f);  
		HSVColorModel hsvConvertedColor = convertRGB2HSV(rgbColor).setBlackValue(percent);
		
		return convertHSV2RGB(hsvConvertedColor).setOpacity(opacity);
	}
	
	public static RGBColorModel convertHSV2RGB(HSVColorModel hsvColor)
	{
		float C, X, m, Rtag, Gtag, Btag;
		if(hsvColor.getH() < 0 || hsvColor.getH() >= 360 || 
			hsvColor.getS() < 0 || hsvColor.getS() > 1 ||
			hsvColor.getV() < 0 || hsvColor.getV() > 1)
			throw new IllegalArgumentException();
		
		C = hsvColor.getV()*hsvColor.getS();
		X = C*(1- Math.abs((hsvColor.getH()/60)%2-1));
		m = hsvColor.getV()-C;
		
		if(hsvColor.getH()>=0 && hsvColor.getH()<60)
		{
			Rtag = C;
			Gtag = X;
			Btag = 0;
		}
		else if(hsvColor.getH()>=60 && hsvColor.getH()<120)
		{
			Rtag = X;
			Gtag = C; 
			Btag = 0; 
		}
		else if(hsvColor.getH()>=120 && hsvColor.getH()<180)
		{
			Rtag = 0;
			Gtag = C; 
			Btag = X; 
		}
		else if(hsvColor.getH()>=180 && hsvColor.getH()<240)
		{
			Rtag = 0;
			Gtag = X; 
			Btag = C;
		}
		else if(hsvColor.getH()>=240 && hsvColor.getH()<300)
		{
			Rtag = X;
			Gtag = 0; 
			Btag = C;
		}
		else
		{
			Rtag = C;
			Gtag = 0; 
			Btag = X;
		}
		
		return RGBColorModel.create((int)((Rtag+m)*255), (int)((Gtag+m)*255), (int)((Btag+m)*255));
	}
	
	public static HSVColorModel convertRGB2HSV(RGBColorModel rgbColor)
	{
		float Cmax, Cmin, delta, H=0, S, V;
		int R = rgbColor.getR();
		float Rtag = R/255f;
		int G = rgbColor.getG();
		float Gtag = G/255f;
		int B = rgbColor.getB();
		float Btag = B/255f;
		
		Cmax = Math.max(Rtag, Math.max(Gtag, Btag));
		Cmin = Math.min(Rtag, Math.min(Gtag, Btag));
		
		delta = Cmax - Cmin;
		
		
		if(delta == 0)
		{
			H = 0;
		}
		else
		{
			if(Cmax == Rtag)
			{
				H = 60*(((Gtag-Btag)/delta)%6);
			}
			else if(Cmax == Gtag)
			{
				H = 60*(((Btag-Rtag)/delta)+2);
			}
			else if(Cmax == Btag)
			{
				H = 60*(((Rtag-Gtag)/delta)+4);
			}
		}
		
		if(Cmax == 0)
		{
			S = 0;
		}
		else
		{
			S = delta/Cmax;
		}
		
		V = Cmax;
		
		return HSVColorModel.create(H,S,V);
	}
	
	public static class HSVColorModel
	{
		private float H,S,V;
		private static final String ERROR_MESSAGE = "Invalid HSV values";
		
		private HSVColorModel(float H, float S, float V)
		{		
			if(V>1 || V <0 || S>1 || S<0 || H >= 360 || H < 0)
				throw new IllegalArgumentException(ERROR_MESSAGE);
			
			this.H = H;
			this.S = S;
			this.V = V;
		}
		
		public static HSVColorModel create(float H, float S, float V)
		{
			return new HSVColorModel(H, S, V);
		}
		
		public float getH()
		{
			return H;
		}

		public float getS()
		{
			return S;
		}

		public float getV()
		{
			return V;
		}
		
		public HSVColorModel setBlackValue(int percent)
		{
			if(percent>100)
				percent=100;
			if(percent<0)
				percent=0;
			
			this.V = this.V * (percent/100f);
			
			return this;
		}
	}
	
	public static class RGBColorModel
	{
		private int R,G,B;
		private Float alpha;
		private static final String ERROR_MESSAGE = "Invalid RGB values"; 

		private RGBColorModel(int R, int G, int B)
		{
			if(R>255 || R<0 || G>255 || G<0 || B>255 || B<0)
				throw new IllegalArgumentException(ERROR_MESSAGE);
			
			this.R = R;
			this.G = G;
			this.B = B;
			this.alpha = null;
		}
		
		@Override
		public String toString()
		{
			String colorString;
			
			if(alpha == null)
			{
				colorString = new StringBuilder().append("#")
												.append(Integer.toHexString(R))
												.append(Integer.toHexString(G))
												.append(Integer.toHexString(B))
												.toString();
			}
			else
			{
				colorString = new StringBuilder().append("rgba(")
												.append(R)
												.append(",")
												.append(G)
												.append(",")
												.append(B)
												.append(",")
												.append(alpha)
												.append(")")
												.toString();
			}
			
			return colorString;
		}
		
		public static RGBColorModel create(int R, int G, int B)
		{
			return new RGBColorModel(R, G, B);
		}
		
		public RGBColorModel setOpacity(float opacity)
		{
			if(opacity < 0 || opacity > 1)
				throw new IllegalArgumentException(ERROR_MESSAGE);
			this.alpha = Float.valueOf(opacity);
			
			return this;
		}
		
		private static RGBColorModel parse(String colorCode) throws IllegalArgumentException
		{
			int R,G,B;
			RGBColorModel res;
			Supplier<IllegalArgumentException> exceptionDef = () -> new IllegalArgumentException(ERROR_MESSAGE);
			String colorCodeFormat1 = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$";
			String colorCodeFormat2 = "(?:\\d{1,3}\\,){3}?([0-9]*\\.[0-9]+|[0-9]+)";
			String colorCodeFormat2Wrapper = "^rgba\\("+colorCodeFormat2+"\\)$";
			String concreteColorCode;
			
			concreteColorCode = Optional.ofNullable(colorCode).orElseThrow(exceptionDef);
			
			if(concreteColorCode.matches(colorCodeFormat1))
			{
				String unwrappedColorCode = concreteColorCode.substring(1);
				R = Integer.parseUnsignedInt(unwrappedColorCode.substring(0, 2), 16);
				G = Integer.parseUnsignedInt(unwrappedColorCode.substring(2, 4), 16);
				B = Integer.parseUnsignedInt(unwrappedColorCode.substring(4, 6), 16);
				
				res = create(R, G, B);
			}
			else if(concreteColorCode.matches(colorCodeFormat2Wrapper))
			{
				Matcher matcher = Pattern.compile(colorCodeFormat2).matcher(concreteColorCode);
				
				matcher.find();
				String unwrappedColorCode = matcher.group(0);
				
				String[] colorParts = unwrappedColorCode.split(",");
				R = Integer.parseInt(colorParts[0]);
				G = Integer.parseInt(colorParts[1]);
				B = Integer.parseInt(colorParts[2]);
				res = create(R, G, B);
				
				if(colorParts.length == 4)
				{
					res.setOpacity(Float.parseFloat(colorParts[3]));
				}
			}
			else
			{
				throw exceptionDef.get();
			}
			
			return res;
		}
		
		
		public static RGBColorModel create(String colorCode) throws IllegalArgumentException
		{
			return parse(colorCode);
		}
		
		public int getR()
		{
			return R;
		}

		public int getG()
		{
			return G;
		}

		public int getB()
		{
			return B;
		}
		
		public Float getAlpha()
		{
			return alpha;
		}
	}

	public enum COLOR
	{
		INDIANRED("#CD5C5C"),
		LIGHTCORAL("#F08080"),
		SALMON("#FA8072"),
		DARKSALMON("#E9967A"),
		LIGHTSALMON("#FFA07A"),
		CRIMSON("#DC143C"),
		RED("#FF0000"),
		FIREBRICK("#B22222"),
		DARKRED("#8B0000"),
		PINK("#FFC0CB"),
		LIGHTPINK("#FFB6C1"),
		HOTPINK("#FF69B4"),
		DEEPPINK("#FF1493"),
		MEDIUMVIOLETRED("#C71585"),
		PALEVIOLETRED("#DB7093"),
		CORAL("#FF7F50"),
		TOMATO("#FF6347"),
		ORANGERED("#FF4500"),
		DARKORANGE("#FF8C00"),
		ORANGE("#FFA500"),
		GOLD("#FFD700"),
		YELLOW("#FFFF00"),
		LIGHTYELLOW("#FFFFE0"),
		LEMONCHIFFON("#FFFACD"),
		LIGHTGOLDENRODYELLOW("#FAFAD2"),
		PAPAYAWHIP("#FFEFD5"),
		MOCCASIN("#FFE4B5"),
		PEACHPUFF("#FFDAB9"),
		PALEGOLDENROD("#EEE8AA"),
		KHAKI("#F0E68C"),
		DARKKHAKI("#BDB76B"),
		LAVENDER("#E6E6FA"),
		THISTLE("#D8BFD8"),
		PLUM("#DDA0DD"),
		VIOLET("#EE82EE"),
		ORCHID("#DA70D6"),
		FUCHSIA("#FF00FF"),
		MAGENTA("#FF00FF"),
		MEDIUMORCHID("#BA55D3"),
		MEDIUMPURPLE("#9370DB"),
		REBECCAPURPLE("#663399"),
		BLUEVIOLET("#8A2BE2"),
		DARKVIOLET("#9400D3"),
		DARKORCHID("#9932CC"),
		DARKMAGENTA("#8B008B"),
		PURPLE("#800080"),
		INDIGO("#4B0082"),
		SLATEBLUE("#6A5ACD"),
		DARKSLATEBLUE("#483D8B"),
		MEDIUMSLATEBLUE("#7B68EE"),
		GREENYELLOW("#ADFF2F"),
		CHARTREUSE("#7FFF00"),
		LAWNGREEN("#7CFC00"),
		LIME("#00FF00"),
		LIMEGREEN("#32CD32"),
		PALEGREEN("#98FB98"),
		LIGHTGREEN("#90EE90"),
		MEDIUMSPRINGGREEN("#00FA9A"),
		SPRINGGREEN("#00FF7F"),
		MEDIUMSEAGREEN("#3CB371"),
		SEAGREEN("#2E8B57"),
		FORESTGREEN("#228B22"),
		GREEN("#008000"),
		DARKGREEN("#006400"),
		YELLOWGREEN("#9ACD32"),
		OLIVEDRAB("#6B8E23"),
		OLIVE("#808000"),
		DARKOLIVEGREEN("#556B2F"),
		MEDIUMAQUAMARINE("#66CDAA"),
		DARKSEAGREEN("#8FBC8B"),
		LIGHTSEAGREEN("#20B2AA"),
		DARKCYAN("#008B8B"),
		TEAL("#008080"),
		AQUA("#00FFFF"),
		CYAN("#00FFFF"),
		LIGHTCYAN("#E0FFFF"),
		PALETURQUOISE("#AFEEEE"),
		AQUAMARINE("#7FFFD4"),
		TURQUOISE("#40E0D0"),
		MEDIUMTURQUOISE("#48D1CC"),
		DARKTURQUOISE("#00CED1"),
		CADETBLUE("#5F9EA0"),
		STEELBLUE("#4682B4"),
		LIGHTSTEELBLUE("#B0C4DE"),
		POWDERBLUE("#B0E0E6"),
		LIGHTBLUE("#ADD8E6"),
		SKYBLUE("#87CEEB"),
		LIGHTSKYBLUE("#87CEFA"),
		DEEPSKYBLUE("#00BFFF"),
		DODGERBLUE("#1E90FF"),
		CORNFLOWERBLUE("#6495ED"),
		ROYALBLUE("#4169E1"),
		BLUE("#0000FF"),
		MEDIUMBLUE("#0000CD"),
		DARKBLUE("#00008B"),
		NAVY("#000080"),
		MIDNIGHTBLUE("#191970"),
		CORNSILK("#FFF8DC"),
		BLANCHEDALMOND("#FFEBCD"),
		BISQUE("#FFE4C4"),
		NAVAJOWHITE("#FFDEAD"),
		WHEAT("#F5DEB3"),
		BURLYWOOD("#DEB887"),
		TAN("#D2B48C"),
		ROSYBROWN("#BC8F8F"),
		SANDYBROWN("#F4A460"),
		GOLDENROD("#DAA520"),
		DARKGOLDENROD("#B8860B"),
		PERU("#CD853F"),
		CHOCOLATE("#D2691E"),
		SADDLEBROWN("#8B4513"),
		SIENNA("#A0522D"),
		BROWN("#A52A2A"),
		MAROON("#800000"),
		WHITE("#FFFFFF"),
		SNOW("#FFFAFA"),
		HONEYDEW("#F0FFF0"),
		MINTCREAM("#F5FFFA"),
		AZURE("#F0FFFF"),
		ALICEBLUE("#F0F8FF"),
		GHOSTWHITE("#F8F8FF"),
		WHITESMOKE("#F5F5F5"),
		SEASHELL("#FFF5EE"),
		BEIGE("#F5F5DC"),
		OLDLACE("#FDF5E6"),
		FLORALWHITE("#FFFAF0"),
		IVORY("#FFFFF0"),
		ANTIQUEWHITE("#FAEBD7"),
		LINEN("#FAF0E6"),
		LAVENDERBLUSH("#FFF0F5"),
		MISTYROSE("#FFE4E1"),
		GAINSBORO("#DCDCDC"),
		LIGHTGRAY("#D3D9E1"),
		SILVER("#C0C0C0"),
		DARKGRAY("#A9A9A9"),
		GRAY("#808080"),
		DIMGRAY("#696969"),
		LIGHTSLATEGRAY("#778899"),
		SLATEGRAY("#708090"),
		DARKSLATEGRAY("#2F4F4F"),
		BLACK("#000000");

		String colorCode;

		private COLOR(String colorCode)
		{
			this.colorCode = colorCode;
		}

		@Override
		public String toString()
		{
			return this.colorCode;
		}
	}
}
