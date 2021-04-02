package dmit2015.wxue3.assignment05.producer;
/**
 * This is logger producer, to display the log of generated by each step
 *
 * @author  Wanlun Xue
 * @version 1.0
 * @lastModified   2021.04.01
 */
import java.util.logging.Logger;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
public class LoggerProducer {
    @Produces
    public Logger produceLogger(InjectionPoint injectionPoint) {
        return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }
}
