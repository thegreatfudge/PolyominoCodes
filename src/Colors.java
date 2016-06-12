import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mgacek on 6/12/16.
 */
public class Colors {
    public static Map<String, Color> colorMap = new HashMap<>();
    Colors(){
        colorMap.put("a", Color.BLUE);
        colorMap.put("b", Color.green);
        colorMap.put("c", Color.cyan);
        colorMap.put("d", Color.magenta);
    }
}
