#!/user/bin/env groovy

def call(Map params) {
  stage('install packages') {
    try {
      if (params.manager == "npm") {
        sh(script: "npm i .", returnStatus: true)
      }
    } catch(Exception e) {
      echo "Exception: ${e}"
    }
  }
}


