package c.tgm.booksapplication.events_for_bus;


import c.tgm.booksapplication.models.data.ServiceInfo;

public class ServiceInfoGotEvent {
    private ServiceInfo mServiceAll;
    private boolean updateMarker = true;
    
    public ServiceInfoGotEvent(ServiceInfo serviceAll) {
        mServiceAll = serviceAll;
    }
    
    public ServiceInfo getServiceAll() {
        return mServiceAll;
    }
    
    public ServiceInfoGotEvent(ServiceInfo serviceAll, boolean updateMarker) {
        mServiceAll = serviceAll;
        this.updateMarker = updateMarker;
    }
    
    public boolean isUpdateMarker() {
        return updateMarker;
    }
}
