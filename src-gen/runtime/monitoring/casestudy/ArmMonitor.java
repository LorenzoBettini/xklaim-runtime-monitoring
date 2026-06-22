package runtime.monitoring.casestudy;

import klava.Locality;
import klava.Tuple;
import klava.topology.KlavaProcess;

@SuppressWarnings("all")
public class ArmMonitor extends KlavaProcess {
  private Locality monitorLoc;

  private Locality safetyLoc;

  private Locality logLoc;

  public ArmMonitor(final Locality monitorLoc, final Locality safetyLoc, final Locality logLoc) {
    this.monitorLoc = monitorLoc;
    this.safetyLoc = safetyLoc;
    this.logLoc = logLoc;
  }

  @Override
  public void executeProcess() {
    Tuple _tuple = new Tuple("NormalRestored");
    Tuple _tuple_1 = new Tuple("WarningDetected");
    Tuple _tuple_2 = new Tuple("EmergencyDetected");
    LevelMonitor _levelMonitor = new LevelMonitor(this.monitorLoc, this.safetyLoc, this.logLoc, _tuple, _tuple_1, _tuple_2);
    this.executeNodeProcess(_levelMonitor);
  }
}
