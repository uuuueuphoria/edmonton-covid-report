package dmit2015.wxue3.assignment05.view;

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

@Named("currentCurrentCasesByLocalGeographicAreaFindOneController")
@ViewScoped
public class CurrentCasesByLocalGeographicAreaFindOneController implements Serializable {

    @Inject
    private CurrentCasesByLocalGeographicAreaRepository _currentcasesbylocalgeographicareaRepository;


    @Getter @Setter
    private double longitude = -113.503519;

    @Getter @Setter
    private double latitude = 53.5678765;

    @Getter
    private CurrentCasesByLocalGeographicArea existingCurrentCasesByLocalGeographicArea;

    @PostConstruct
    public void init() {
        if (!Faces.isPostback()) {
            Optional<CurrentCasesByLocalGeographicArea> optionalEntity = _currentcasesbylocalgeographicareaRepository.contains(longitude,latitude);
            optionalEntity.ifPresent(entity -> existingCurrentCasesByLocalGeographicArea = entity);
        }
    }

    public void onSearch() {
        try {
            Optional<CurrentCasesByLocalGeographicArea> optionalEntity = _currentcasesbylocalgeographicareaRepository.contains(longitude,latitude);
            if (optionalEntity.isPresent()) {
                existingCurrentCasesByLocalGeographicArea = optionalEntity.get();
            } else {
                existingCurrentCasesByLocalGeographicArea = null;
            }
        } catch (Exception ex) {
            Messages.addGlobalError(ex.getMessage());
        }
    }

    public void onClear() {
        existingCurrentCasesByLocalGeographicArea = null;
    }




}
