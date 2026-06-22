package runtime.monitoring;

import java.util.List;
import klava.Locality;
import klava.Tuple;
import klava.topology.KlavaOrProcess;
import klava.topology.KlavaProcess;

@SuppressWarnings("all")
public class SafeExecuteTimeout extends KlavaProcess {
  private KlavaProcess P;

  private Locality safetyLoc;

  private long timeout;

  public SafeExecuteTimeout(final KlavaProcess P, final Locality safetyLoc, final long timeout) {
    this.P = P;
    this.safetyLoc = safetyLoc;
    this.timeout = timeout;
  }

  @Override
  public void executeProcess() {
    KlavaOrProcess _OrProc = new KlavaOrProcess() {
      Locality safetyLoc;
      KlavaProcess P;
      private KlavaOrProcess _initFields(Locality safetyLoc, KlavaProcess P) {
        this.safetyLoc = safetyLoc;
        this.P = P;
        return this;
      }
      @Override public void executeProcess() {
        {
          read(new Tuple(new Object[] {"Clear"}), this.safetyLoc);
          this.executeNodeProcess(this.P);
        }
      }
    }._initFields(safetyLoc, P);
    KlavaOrProcess _OrProc_1 = new KlavaOrProcess() {
      Locality safetyLoc;
      long timeout;
      KlavaProcess P;
      private KlavaOrProcess _initFields(Locality safetyLoc, long timeout, KlavaProcess P) {
        this.safetyLoc = safetyLoc;
        this.timeout = timeout;
        this.P = P;
        return this;
      }
      @Override public void executeProcess() {
        {
          read(new Tuple(new Object[] {"Stop"}), this.safetyLoc);
          boolean _read = read_t(new Tuple(new Object[] {"Clear"}), this.safetyLoc, this.timeout);
          if (_read) {
            this.executeNodeProcess(this.P);
          }
        }
      }
    }._initFields(safetyLoc, timeout, P);
    or(List.of(_OrProc, _OrProc_1));
  }
}
