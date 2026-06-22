package runtime.monitoring.casestudy;

@SuppressWarnings("all")
public class CaseStudyExampleMain {
  public static void main(final String[] args) throws Exception {
    CaseStudyExampleNet caseStudyExampleNet = new CaseStudyExampleNet();
    caseStudyExampleNet.addNodes();
    caseStudyExampleNet.waitForCompletion();
  }
}
