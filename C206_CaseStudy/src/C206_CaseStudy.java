import java.time.LocalDateTime;
        import java.util.ArrayList;
		import java.util.List;
		
public class C206_CaseStudy {
	
	    public static void main(String[] args) {
	        User user = new User();
	        RenovationService service = new RenovationService();

	        AppointmentScheduler appointmentScheduler = new AppointmentScheduler();

	        LocalDateTime startTime = LocalDateTime.now();
	        LocalDateTime endTime = startTime.plusHours(2);

	        boolean isScheduled = appointmentScheduler.scheduleAppointment(startTime, endTime, user, service);

	        if (isScheduled) {
	            System.out.println("Appointment scheduled successfully.");
	        } else {
	            System.out.println("Appointment scheduling failed.");
	        }

	        List<Appointment> userAppointments = appointmentScheduler.getAppointmentsForUser(user);
	        System.out.println("User Appointments:");
	        for (Appointment appointment : userAppointments) {
	            System.out.println("Service: " + appointment.getService().getServiceName() +
	                               ", Start Time: " + appointment.getStartTime() +
	                               ", Status: " + appointment.getStatus());
	        }

	        if (!userAppointments.isEmpty()) {
	            Appointment appointmentToCancel = userAppointments.get(0);
	            if (appointmentScheduler.cancelAppointment(appointmentToCancel)) {
	                System.out.println("Appointment canceled successfully.");
	            } else {
	                System.out.println("Appointment cancellation failed.");
	            }
	        }
	    }

		class User {
		    private String username;
		    // Other user-related fields, getters, setters
		}

		class RenovationService {
		    private String serviceName;
		    // Other service-related fields, getters, setters

			public String getServiceName() {
				// TODO Auto-generated method stub
				return null;
			}
		}

		enum AppointmentStatus {
		    SCHEDULED,
		    CANCELED
		}

		class Appointment {
		    private LocalDateTime startTime;
		    private LocalDateTime endTime;
		    private User user;
		    private RenovationService service;
		    private AppointmentStatus status;

		    public Appointment(LocalDateTime startTime, LocalDateTime endTime, User user, RenovationService service) {
		        this.startTime = startTime;
		        this.endTime = endTime;
		        this.user = user;
		        this.service = service;
		        this.status = AppointmentStatus.SCHEDULED;
		    }

		    public void cancelAppointment() {
		        this.status = AppointmentStatus.CANCELED;
		    }

			public Object getUser() {
				// TODO Auto-generated method stub
				return null;
			}
			 public LocalDateTime getStartTime() {
			        return startTime;
			    }

			    public LocalDateTime getEndTime() {
			        return endTime;
			    }

			    public RenovationService getService() {
			        return service;
			    }

			    public AppointmentStatus getStatus() {
			        return status;
			    }
			    
			    public String getServiceName() {
			        return service.getServiceName();
			    }


		    // Getters and setters
		}

		class AppointmentScheduler {
		    private List<Appointment> appointments;

		    public AppointmentScheduler() {
		        appointments = new ArrayList<>();
		    }

		    public boolean scheduleAppointment(LocalDateTime startTime, LocalDateTime endTime, User user, RenovationService service) {
		        Appointment newAppointment = new Appointment(startTime, endTime, user, service);
		        if (isAppointmentAvailable(newAppointment)) {
		            appointments.add(newAppointment);
		            return true;
		        }
		        return false;
		    }

		    public List<Appointment> getAppointmentsForUser(User user) {
		        List<Appointment> userAppointments = new ArrayList<>();
		        for (Appointment appointment : appointments) {
		            if (appointment.getUser().equals(user)) {
		                userAppointments.add(appointment);
		            }
		        }
		        return userAppointments;
		    }

		    public boolean cancelAppointment(Appointment appointment) {
		        if (appointments.contains(appointment)) {
		            appointment.cancelAppointment();
		            return true;
		        }
		        return false;
		    }

		    private boolean isAppointmentAvailable(Appointment appointment) {
		        for (Appointment existingAppointment : appointments) {
		            if (existingAppointment.getStartTime().isBefore(appointment.getEndTime()) &&
		                existingAppointment.getEndTime().isAfter(appointment.getStartTime())) {
		                return false; // Conflict found
		            }
		        }
		        return true; // No conflict
		    }
		}
}
		


