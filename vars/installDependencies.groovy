#!/user/bin/env groovy

def call(Map params) {
  stage('install packages') {
    try {
      if (params.manager == "npm") {
        configFileProvider([configFile(fileId: '17d3db00-8ec7-4598-8b81-b5a4e25a0d2f', variable: 'npm_config_registry')]) {
          sh(script: "npm i .", returnStatus: true)
        }
      }
    } catch(Exception e) {
      echo "Exception: ${e}"
    }
  }
}


