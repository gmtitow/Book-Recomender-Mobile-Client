package c.tgm.booksapplication.events_for_bus;

public class OnServiceElementSelected {
    private long serviceId;
    
    public OnServiceElementSelected(long serviceId) {
        this.serviceId = serviceId;
    }
    
    public long getServiceId() {
        return serviceId;
    }
}
