#!/user/bin/env groovy

def call(Map params) {
  stage('unit tests') {
    try {
      if (params.manager == "npm") {
        sh(script: "npm test", returnStatus: true)
      }
    } catch(Exception e) {
      echo "Exception: ${e}"
    }
  }
}


