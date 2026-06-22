package runtime.monitoring.casestudy;

import java.util.List;
import klava.Locality;
import klava.Tuple;
import klava.topology.KlavaOrProcess;
import klava.topology.KlavaProcess;

@SuppressWarnings("all")
public class LevelGuardedExecute extends KlavaProcess {
  private Locality safetyLoc;

  private Locality logLoc;

  private KlavaProcess normalP;

  private KlavaProcess reducedP;

  private KlavaProcess haltP;

  public LevelGuardedExecute(final Locality safetyLoc, final Locality logLoc, final KlavaProcess normalP, final KlavaProcess reducedP, final KlavaProcess haltP) {
    this.safetyLoc = safetyLoc;
    this.logLoc = logLoc;
    this.normalP = normalP;
    this.reducedP = reducedP;
    this.haltP = haltP;
  }

  @Override
  public void executeProcess() {
    KlavaOrProcess _OrProc = new KlavaOrProcess() {
      Locality safetyLoc;
      KlavaProcess normalP;
      private KlavaOrProcess _initFields(Locality safetyLoc, KlavaProcess normalP) {
        this.safetyLoc = safetyLoc;
        this.normalP = normalP;
        return this;
      }
      @Override public void executeProcess() {
        {
          read(new Tuple(new Object[] {"Level", 0}), this.safetyLoc);
          this.executeNodeProcess(this.normalP);
        }
      }
    }._initFields(safetyLoc, normalP);
    KlavaOrProcess _OrProc_1 = new KlavaOrProcess() {
      Locality safetyLoc;
      KlavaProcess reducedP;
      private KlavaOrProcess _initFields(Locality safetyLoc, KlavaProcess reducedP) {
        this.safetyLoc = safetyLoc;
        this.reducedP = reducedP;
        return this;
      }
      @Override public void executeProcess() {
        {
          read(new Tuple(new Object[] {"Level", 1}), this.safetyLoc);
          this.executeNodeProcess(this.reducedP);
        }
      }
    }._initFields(safetyLoc, reducedP);
    KlavaOrProcess _OrProc_2 = new KlavaOrProcess() {
      Locality safetyLoc;
      KlavaProcess haltP;
      private KlavaOrProcess _initFields(Locality safetyLoc, KlavaProcess haltP) {
        this.safetyLoc = safetyLoc;
        this.haltP = haltP;
        return this;
      }
      @Override public void executeProcess() {
        {
          read(new Tuple(new Object[] {"Level", 2}), this.safetyLoc);
          this.executeNodeProcess(this.haltP);
        }
      }
    }._initFields(safetyLoc, haltP);
    or(List.of(_OrProc, _OrProc_1, _OrProc_2));
  }
}
