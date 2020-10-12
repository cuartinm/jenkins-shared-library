#!/user/bin/env groovy

def call(Map params) {
  stage('install packages') {
    try {
      sh(script: "npm i .", returnStatus: true)
    } catch(Exception e) {
      echo "Exception: ${e}"
    }
  }
}


