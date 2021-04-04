#!/user/bin/env groovy

def call(Map params) {
  def builder = new groovy.json.JsonBuilder()
  builder key: params.key, state: params.state, description: params.description, url: params.url
  
  def httpConn = new URL(params.url).openConnection();
  httpConn.setRequestMethod("POST");
  httpConn.setRequestProperty("Authorization", "Basic ${params.credentials}")
  httpConn.setRequestProperty("Accept", "application/json");
  httpConn.setDoOutput(true);
  httpConn.getOutputStream().write(builder.toString().getBytes("UTF-8"));
  return httpConn.getResponseCode();
}
