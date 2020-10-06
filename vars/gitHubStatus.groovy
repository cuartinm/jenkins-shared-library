#!/user/bin/env groovy

def call(Map params) {
  def builder = new groovy.json.JsonBuilder()
  builder context: params.context, state: params.state, description: params.$description, target_url: params.target_url
  try {
    def httpConn = new URL(params.statuses_url).openConnection();
    httpConn.setRequestMethod("POST");
    httpConn.setRequestProperty("Authorization", "token ${params.github_access_token}")
    httpConn.setRequestProperty("Accept", "application/vnd.github.v3+json")
    httpConn.setRequestProperty("Accept", "application/json");
    httpConn.setDoOutput(true);
    httpConn.getOutputStream().write(builder.toString().getBytes("UTF-8"));
    return httpConn.getResponseCode();
  } catch(Exception e){
    echo "Exception: ${e}"
    currentBuild.result = 'FAILURE'
  } 
}
