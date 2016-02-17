public static String getImgFromUrl(String urlstr, String savenme) throws IOException{
		 URL url2 = new URL(urlstr);  
		 HttpURLConnection connection2 = (HttpURLConnection) url2.openConnection();
		 connection2.setRequestProperty("Cookie", "zbx_sessionid="+auth);
		 
		 File imgCodeFile = new File(savenme); 
		 connection2.connect();
		 BufferedImage image = ImageIO.read(connection2.getInputStream()); 
		 ImageIO.write(image, "gif", imgCodeFile);
		 return "";
	 }
	 
	 public static String getItemId(String hostid,String key_,String auth){
		 String s=getpost.sendPost("http://192.168.3.20/zabbix/api_jsonrpc.php", "{\"jsonrpc\": \"2.0\",\"method\":\"item.get\",\"params\":{\"output\":\"itemids\",\"hostids\":"+hostid+",\"search\":{\"key_\":\""+key_+"\"}},\"auth\":\""+auth+"\",\"id\": 0}");
		 JSONObject obj;
		 JSONArray oay;
		 try {
				obj = new JSONObject(s);
				oay=(JSONArray) obj.get("result");
				obj=new JSONObject(oay.getString(0));
				return obj.getString("itemid");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 return "error";
	 
	 }
	 public static String getHostidByip(String ip,String auth){
		 String s=getpost.sendPost("http://192.168.3.20/zabbix/api_jsonrpc.php", "{\"jsonrpc\":\"2.0\",\"method\":\"host.get\",\"params\":{\"output\":\"extend\",\"search\":{\"name\":\""+ip+"\"}},\"auth\":\""+auth+"\",\"id\":0}");
		 JSONObject obj;
		 JSONArray oay;
		 try {
				obj = new JSONObject(s);
				oay=(JSONArray) obj.get("result");
				obj=new JSONObject(oay.getString(0));
				return obj.getString("hostid");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 return "error";
	 }
	 public static String login(){
		 String s=getpost.sendPost("http://192.168.3.20/zabbix/api_jsonrpc.php","{\"jsonrpc\":\"2.0\",\"method\":\"user.login\",\"params\":{\"password\":\"zabbix\",\"user\":\"Admin\"},\"id\":1}");
		 JSONObject obj;
		try {
			obj = new JSONObject(s);
			return obj.getString("result");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "error";
		 
	 }
