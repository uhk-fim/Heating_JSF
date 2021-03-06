package cz.bartos.heating.beans;

import cz.bartos.heating.dao.RoomDao;
import cz.bartos.heating.domain.Room;
import cz.bartos.heating.producer.RoomProducer;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Pavel Pscheidl
 */
@Named
@ViewScoped
public class AddRoomBean implements Serializable {

    @Inject private RoomProducer roomProducer;
    @Inject private RoomDao roomDao;
    private Room addedRoom;

    @PostConstruct
    public void init() {
        addedRoom = roomProducer.produceSpecimenRoom();
    }

    public String submitRoom() {
        roomDao.save(addedRoom);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Místnost přidána", "Vaše místnost byla uložena");
        FacesContext.getCurrentInstance().addMessage(null, message);
        addedRoom = roomProducer.produceSpecimenRoom();
        return "";
    }

}
