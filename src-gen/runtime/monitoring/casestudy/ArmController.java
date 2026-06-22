package runtime.monitoring.casestudy;

import klava.Locality;
import klava.Tuple;
import klava.topology.KlavaProcess;

@SuppressWarnings("all")
public class ArmController extends KlavaProcess {
  private Locality safetyLoc;

  private Locality logLoc;

  private Locality jointsLoc;

  private Locality gripperLoc;

  private Coordinate pickCoordinate;

  private Coordinate placeCoordinate;

  public ArmController(final Locality safetyLoc, final Locality logLoc, final Locality jointsLoc, final Locality gripperLoc, final Coordinate pickCoordinate, final Coordinate placeCoordinate) {
    this.safetyLoc = safetyLoc;
    this.logLoc = logLoc;
    this.jointsLoc = jointsLoc;
    this.gripperLoc = gripperLoc;
    this.pickCoordinate = pickCoordinate;
    this.placeCoordinate = placeCoordinate;
  }

  @Override
  public void executeProcess() {
    KlavaProcess _Proc = new KlavaProcess() {
      Locality gripperLoc;
      private KlavaProcess _initFields(Locality gripperLoc) {
        this.gripperLoc = gripperLoc;
        return this;
      }
      @Override public void executeProcess() {
        out(new Tuple(new Object[] {"Grasp"}), this.gripperLoc);
      }
    }._initFields(gripperLoc);
    ArmControllerMoveOperation _armControllerMoveOperation = new ArmControllerMoveOperation(this.safetyLoc, this.logLoc, this.jointsLoc, this.pickCoordinate, _Proc);
    this.executeNodeProcess(_armControllerMoveOperation);
    Boolean pickResult = null;
    Tuple _Tuple = new Tuple(new Object[] {"Arm", "MoveResult", Boolean.class});
    in(_Tuple, this.self);
    pickResult = (Boolean) _Tuple.getItem(2);
    if ((!(pickResult).booleanValue())) {
      out(new Tuple(new Object[] {"ArmController", "Pick", "Aborted"}), this.logLoc);
      return;
    }
    out(new Tuple(new Object[] {"ArmController", "Pick", "OK"}), this.logLoc);
    KlavaProcess _Proc_1 = new KlavaProcess() {
      Locality gripperLoc;
      private KlavaProcess _initFields(Locality gripperLoc) {
        this.gripperLoc = gripperLoc;
        return this;
      }
      @Override public void executeProcess() {
        out(new Tuple(new Object[] {"Release"}), this.gripperLoc);
      }
    }._initFields(gripperLoc);
    ArmControllerMoveOperation _armControllerMoveOperation_1 = new ArmControllerMoveOperation(this.safetyLoc, this.logLoc, this.jointsLoc, this.placeCoordinate, _Proc_1);
    this.executeNodeProcess(_armControllerMoveOperation_1);
    Boolean placeResult = null;
    Tuple _Tuple_1 = new Tuple(new Object[] {"Arm", "MoveResult", Boolean.class});
    in(_Tuple_1, this.self);
    placeResult = (Boolean) _Tuple_1.getItem(2);
    if ((!(placeResult).booleanValue())) {
      out(new Tuple(new Object[] {"ArmController", "Place", "Aborted"}), this.logLoc);
      return;
    }
    out(new Tuple(new Object[] {"ArmController", "Place", "OK"}), this.logLoc);
  }
}
