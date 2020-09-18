#!/user/bin/env groovy

def call(Map stageParams) {
    def builder = new groovy.json.JsonBuilder()
    builder context: stageParams.context, state: stageParams.state, description: stageParams.$description, target_url: stageParams.target_url
    try {
      def httpConn = new URL(stageParams.statuses_url).openConnection();
      httpConn.setRequestMethod("POST");
      httpConn.setRequestProperty("Authorization", "token ${stageParams.github_access_token}")
      httpConn.setRequestProperty("Accept", "application/vnd.github.v3+json")
      httpConn.setRequestProperty("Accept", "application/json");
      httpConn.setDoOutput(true);
      httpConn.getOutputStream().write(builder.toString().getBytes("UTF-8"));
      return httpConn.getResponseCode();
    } catch(Exception e){
      echo "Exception: ${e}"
    }  
}