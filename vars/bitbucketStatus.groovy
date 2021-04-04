#!/user/bin/env groovy

def call(Map params) {
  withCredentials([usernameColonPassword(credentialsId: '2c979272-3921-4796-9f44-82b564ce8f3f', variable: 'BITBUCKET_CREDS')]) {
    def builder = new groovy.json.JsonBuilder()
    builder key: params.key, state: params.state, description: params.description, url: params.url
    
    def httpConn = new URL(params.statuses_url).openConnection();
    httpConn.setRequestMethod("POST");
    httpConn.setRequestProperty("Authorization", "Basic ${BITBUCKET_CREDS}")
    httpConn.setRequestProperty("Accept", "application/vnd.github.v3+json")
    httpConn.setRequestProperty("Accept", "application/json");
    httpConn.setDoOutput(true);
    httpConn.getOutputStream().write(builder.toString().getBytes("UTF-8"));
    return httpConn.getResponseCode();
  }
}
