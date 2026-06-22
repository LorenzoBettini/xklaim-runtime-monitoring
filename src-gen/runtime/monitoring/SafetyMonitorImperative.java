package runtime.monitoring;

import java.util.List;
import klava.Locality;
import klava.Tuple;
import klava.topology.KlavaOrProcess;
import klava.topology.KlavaProcess;
import org.eclipse.xtext.xbase.lib.InputOutput;

@SuppressWarnings("all")
public class SafetyMonitorImperative extends KlavaProcess {
  private Locality monitorLoc;

  private Locality safetyLoc;

  public SafetyMonitorImperative(final Locality monitorLoc, final Locality safetyLoc) {
    this.monitorLoc = monitorLoc;
    this.safetyLoc = safetyLoc;
  }

  @Override
  public void executeProcess() {
    while ((!Thread.interrupted())) {
      KlavaOrProcess _OrProc = new KlavaOrProcess() {
        Locality monitorLoc;
        Locality safetyLoc;
        private KlavaOrProcess _initFields(Locality monitorLoc, Locality safetyLoc) {
          this.monitorLoc = monitorLoc;
          this.safetyLoc = safetyLoc;
          return this;
        }
        @Override public void executeProcess() {
          {
            in(new Tuple(new Object[] {"SafetyConditionDetected"}), this.monitorLoc);
            InputOutput.<String>println("SAFETY MONITOR: SafetyConditionDetected");
            String current = null;
            Tuple _Tuple = new Tuple(new Object[] {String.class});
            in(_Tuple, this.safetyLoc);
            current = (String) _Tuple.getItem(0);
            out(new Tuple(new Object[] {"Stop"}), this.safetyLoc);
          }
        }
      }._initFields(monitorLoc, safetyLoc);
      KlavaOrProcess _OrProc_1 = new KlavaOrProcess() {
        Locality monitorLoc;
        Locality safetyLoc;
        private KlavaOrProcess _initFields(Locality monitorLoc, Locality safetyLoc) {
          this.monitorLoc = monitorLoc;
          this.safetyLoc = safetyLoc;
          return this;
        }
        @Override public void executeProcess() {
          {
            in(new Tuple(new Object[] {"SafetyConditionCleared"}), this.monitorLoc);
            InputOutput.<String>println("SAFETY MONITOR: SafetyConditionCleared");
            String current = null;
            Tuple _Tuple = new Tuple(new Object[] {String.class});
            in(_Tuple, this.safetyLoc);
            current = (String) _Tuple.getItem(0);
            out(new Tuple(new Object[] {"Clear"}), this.safetyLoc);
          }
        }
      }._initFields(monitorLoc, safetyLoc);
      or(List.of(_OrProc, _OrProc_1));
    }
  }
}
