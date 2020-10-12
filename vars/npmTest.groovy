#!/user/bin/env groovy

def call(Map params) {
  stage('unit tests') {
    try {
      sh(script: "npm test", returnStatus: true)
    } catch(Exception e) {
      echo "Exception: ${e}"
    }
  }
}


