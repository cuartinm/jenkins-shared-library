#!/user/bin/env groovy

def call(Map params) {
  stage('build artifacts') {
    try {
      sh(script: "npm run-script build", returnStatus: true)
      def props = readJSON file: 'package.json'
      dir("dist") {
        sh "tar -czvf ${props.name}-${name.version}.tar.gz ${props.name}/"
      }
    } catch(Exception e) {
      echo "Exception: ${e}"
    }
  }
}


