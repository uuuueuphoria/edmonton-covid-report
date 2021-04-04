package dmit2015.wxue3.assignment05.view;
/**
 * This is the edit controller, use to read the detail of a specific location
 *
 * @author  Wanlun Xue
 * @version 1.0
 * @lastModified   2021.04.03
 */
import dmit2015.wxue3.assignment05.entity.CurrentCasesByLocalGeographicArea;
import dmit2015.wxue3.assignment05.repository.CurrentCasesByLocalGeographicAreaRepository;
import lombok.Getter;
import lombok.Setter;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import javax.annotation.PostConstruct;
import javax.faces.annotation.ManagedProperty;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Optional;

@Named("currentCurrentCasesByLocalGeographicAreaEditController")
@ViewScoped
public class CurrentCasesByLocalGeographicAreaEditController implements Serializable {

    @Inject
    private CurrentCasesByLocalGeographicAreaRepository _currentcasesbylocalgeographicareaRepository;

    @Inject
    @ManagedProperty("#{param.longitude}")
    @Getter
    @Setter
    private double longitude;

    @Inject
    @ManagedProperty("#{param.latitude}")
    @Getter
    @Setter
    private double latitude;

    @Getter
    private CurrentCasesByLocalGeographicArea existingCurrentCasesByLocalGeographicArea;


    @PostConstruct
    public void init() {
        if (!Faces.isPostback()) {
            existingCurrentCasesByLocalGeographicArea = _currentcasesbylocalgeographicareaRepository.contains(longitude, latitude);
        }
    }




}
