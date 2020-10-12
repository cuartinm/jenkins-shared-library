#!/user/bin/env groovy

def call(Map params) {
  stage('build artifacts') {
    try {
      sh(script: "npm run-script build", returnStatus: true)
    } catch(Exception e) {
      echo "Exception: ${e}"
    }
  }
}


