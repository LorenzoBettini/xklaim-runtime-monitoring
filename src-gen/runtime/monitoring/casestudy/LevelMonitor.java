package runtime.monitoring.casestudy;

import java.util.List;
import klava.Locality;
import klava.Tuple;
import klava.topology.KlavaOrProcess;
import klava.topology.KlavaProcess;

@SuppressWarnings("all")
public class LevelMonitor extends KlavaProcess {
  private Locality monitorLoc;

  private Locality safetyLoc;

  private Locality logLoc;

  private Tuple normalTuple;

  private Tuple warningTuple;

  private Tuple emergencyTuple;

  public LevelMonitor(final Locality monitorLoc, final Locality safetyLoc, final Locality logLoc, final Tuple normalTuple, final Tuple warningTuple, final Tuple emergencyTuple) {
    this.monitorLoc = monitorLoc;
    this.safetyLoc = safetyLoc;
    this.logLoc = logLoc;
    this.normalTuple = normalTuple;
    this.warningTuple = warningTuple;
    this.emergencyTuple = emergencyTuple;
  }

  @Override
  public void executeProcess() {
    while ((!Thread.interrupted())) {
      KlavaOrProcess _OrProc = new KlavaOrProcess() {
        Tuple normalTuple;
        Locality monitorLoc;
        Locality safetyLoc;
        Locality logLoc;
        private KlavaOrProcess _initFields(Tuple normalTuple, Locality monitorLoc, Locality safetyLoc, Locality logLoc) {
          this.normalTuple = normalTuple;
          this.monitorLoc = monitorLoc;
          this.safetyLoc = safetyLoc;
          this.logLoc = logLoc;
          return this;
        }
        @Override public void executeProcess() {
          {
            in(this.normalTuple, this.monitorLoc);
            Integer k = null;
            Tuple _Tuple = new Tuple(new Object[] {"Level", Integer.class});
            in(_Tuple, this.safetyLoc);
            k = (Integer) _Tuple.getItem(1);
            out(new Tuple(new Object[] {"Level", 0}), this.safetyLoc);
            long _currentTimeMillis = System.currentTimeMillis();
            out(new Tuple(new Object[] {"Event", "LevelRestored", k, 0, _currentTimeMillis}), this.logLoc);
          }
        }
      }._initFields(normalTuple, monitorLoc, safetyLoc, logLoc);
      KlavaOrProcess _OrProc_1 = new KlavaOrProcess() {
        Tuple warningTuple;
        Locality monitorLoc;
        Locality safetyLoc;
        Locality logLoc;
        private KlavaOrProcess _initFields(Tuple warningTuple, Locality monitorLoc, Locality safetyLoc, Locality logLoc) {
          this.warningTuple = warningTuple;
          this.monitorLoc = monitorLoc;
          this.safetyLoc = safetyLoc;
          this.logLoc = logLoc;
          return this;
        }
        @Override public void executeProcess() {
          {
            in(this.warningTuple, this.monitorLoc);
            Integer k = null;
            Tuple _Tuple = new Tuple(new Object[] {"Level", Integer.class});
            in(_Tuple, this.safetyLoc);
            k = (Integer) _Tuple.getItem(1);
            out(new Tuple(new Object[] {"Level", 1}), this.safetyLoc);
            long _currentTimeMillis = System.currentTimeMillis();
            out(new Tuple(new Object[] {"Event", "LevelRaised", k, 1, _currentTimeMillis}), this.logLoc);
          }
        }
      }._initFields(warningTuple, monitorLoc, safetyLoc, logLoc);
      KlavaOrProcess _OrProc_2 = new KlavaOrProcess() {
        Tuple emergencyTuple;
        Locality monitorLoc;
        Locality safetyLoc;
        Locality logLoc;
        private KlavaOrProcess _initFields(Tuple emergencyTuple, Locality monitorLoc, Locality safetyLoc, Locality logLoc) {
          this.emergencyTuple = emergencyTuple;
          this.monitorLoc = monitorLoc;
          this.safetyLoc = safetyLoc;
          this.logLoc = logLoc;
          return this;
        }
        @Override public void executeProcess() {
          {
            in(this.emergencyTuple, this.monitorLoc);
            Integer k = null;
            Tuple _Tuple = new Tuple(new Object[] {"Level", Integer.class});
            in(_Tuple, this.safetyLoc);
            k = (Integer) _Tuple.getItem(1);
            out(new Tuple(new Object[] {"Level", 2}), this.safetyLoc);
            long _currentTimeMillis = System.currentTimeMillis();
            out(new Tuple(new Object[] {"Event", "LevelRaised", k, 2, _currentTimeMillis}), this.logLoc);
          }
        }
      }._initFields(emergencyTuple, monitorLoc, safetyLoc, logLoc);
      or(List.of(_OrProc, _OrProc_1, _OrProc_2));
    }
  }
}
