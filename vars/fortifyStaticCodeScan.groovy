#!/user/bin/env groovy

def call(Map params) {
  stage('fortify scan') {
    // Remove all existing Fortify StaticCode Analyzer temporary files for the specified buildID.
    sh "sourceanalyzer -b ${params.buildId} -clean"
    
    // Translate the project code.
    sh "sourceanalyzer -b ${params.buildId} -Dcom.fortify.sca.EnableDOMModeling=true -Dcom.fortify.sca.hoa.Enable=true ${params.src}"
    sh "sourceanalyzer -b ${params.buildId} -show-build-warnings"
    sh "sourceanalyzer -b ${params.buildId} -show-files"

    // Analyze the project code and produce the results file
    sh "sourceanalyzer -b ${params.buildId} -scan -f results.fpr"

    sh "FPRUtility -information -analyzerIssueCounts -project results.fpr"
    sh "FPRUtility -categoryIssueCounts -listIssues -information -search -query '[fortify priority order]:critical OR ([fortify priority order]:high AND ([analyzer]:structural OR [analyzer]:data flow OR [analyzer]:content OR [analyzer]:semantic))' -project results.fpr"
    // securely transfer objects to and from Fortify Software Security Center
    // sh "fortifyclient -url ${params.sscurl} -authtoken ${params.ssctoken} uploadFPR -f results.fpr -project ${sscproject} -version ${sscversion}"
  }
}
