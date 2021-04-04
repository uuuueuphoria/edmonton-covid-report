package dmit2015.wxue3.assignment05.ejb;
/**
 * This is java backing bean class with methods to access a JSF web page to cancel/list timers
 *
 * @author  Wanlun Xue
 * @version 1.0
 * @lastModified   2021.04.03
 */
import javax.ejb.Timer;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Collection;

@Named
@RequestScoped
public class TimerController {
    private static final long serialVersionUID = 1L;

    @Inject
    private ProgrammaticTimersManagerBean timerBean;

    public String cancelAllTimers() {
        timerBean.cancelAllTimers();;
        return "";
    }

    public Collection<Timer> list() {
        return timerBean.listAllTimers();
    }

    public void cancelTimer(Timer selectedTimer) {
        timerBean.cancelTimer(selectedTimer);
    }
}