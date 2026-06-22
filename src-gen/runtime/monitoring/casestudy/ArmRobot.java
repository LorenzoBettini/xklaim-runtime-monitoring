package runtime.monitoring.casestudy;

import java.util.Objects;
import klava.Locality;
import klava.Tuple;
import klava.topology.KlavaProcess;
import org.eclipse.xtext.xbase.lib.InputOutput;

@SuppressWarnings("all")
public class ArmRobot extends KlavaProcess {
  private Locality jointsLoc;

  private Locality gripperLoc;

  public ArmRobot(final Locality jointsLoc, final Locality gripperLoc) {
    this.jointsLoc = jointsLoc;
    this.gripperLoc = gripperLoc;
  }

  @Override
  public void executeProcess() {
    while ((!Thread.interrupted())) {
      {
        String moveAct = null;
        Coordinate target = null;
        Tuple _Tuple = new Tuple(new Object[] {"Move", String.class, Coordinate.class});
        in(_Tuple, this.jointsLoc);
        moveAct = (String) _Tuple.getItem(1);
        target = (Coordinate) _Tuple.getItem(2);
        InputOutput.<String>println(((("ArmRobot Move(" + target) + "): ") + moveAct));
        boolean _notEquals = (!Objects.equals(moveAct, "Halt"));
        if (_notEquals) {
          String act = null;
          Tuple _Tuple_1 = new Tuple(new Object[] {String.class});
          in(_Tuple_1, this.gripperLoc);
          act = (String) _Tuple_1.getItem(0);
          InputOutput.<String>println(("ArmRobot: " + act));
        }
      }
    }
  }
}
