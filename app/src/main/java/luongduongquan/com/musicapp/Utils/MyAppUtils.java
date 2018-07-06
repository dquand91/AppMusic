package luongduongquan.com.musicapp.Utils;

public class MyAppUtils {

	public static final String KEY_INTENT_BANNER = "banner";
	public static final String KEY_INTENT_PLAYLIST = "playlist";
	public static final String KEY_INTENT_THELOAI = "theloai";
	public static final String KEY_INTENT_CHUDE = "chude";
	public static final String KEY_INTENT_ALBUM = "album";
	public static final String KEY_INTENT_BAIHAT = "baihat";
	public static final String KEY_INTENT_LIST_BAIHAT = "listbaihat";

	public static String replaceHTTPStoHTTP(String httpsString){
		return httpsString.replace("https", "http");
	}

}
