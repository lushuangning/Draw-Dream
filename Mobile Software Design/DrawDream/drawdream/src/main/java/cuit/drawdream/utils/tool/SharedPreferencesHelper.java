package cuit.drawdream.utils.tool;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * SharedPreferences
 * 
 * @author
 *
 */
public class SharedPreferencesHelper {
	/**
	 * 读取
	 */

	public static void ReadSharedPreferences(Context context) {
		SharedPreferences preferences = context.getSharedPreferences(
				"my_datas", Context.MODE_PRIVATE);
		Config.isActive = preferences.getBoolean("isActive",false);
	}

	/**
	 * 写入
	 */

	public static void WriteSharedPreferences(Context context) {
		SharedPreferences preferences = context.getSharedPreferences(
				"my_datas", Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = preferences.edit();
		editor.putBoolean("isActive", Config.isActive);
		editor.commit();
	}

}
