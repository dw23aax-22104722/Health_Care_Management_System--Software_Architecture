package Model;

import java.util.*;

public class General_Practitioner extends Clinician
{
    private ArrayList <Appointment> appointments = new ArrayList<>();
    private ArrayList <Referral> referrals = new ArrayList<>();

    public General_Practitioner(String clinicianId,
                               String firstName,
                               String lastName,
                               String title,
                               String speciality,
                               String phoneNumber,
                               String email,
                               String workplaceId,
                               String workplaceType,
                               String employmentStatus,
                               String startDate) {
        super(clinicianId, firstName, lastName, title, speciality
                , phoneNumber, email,
                workplaceId, workplaceType, employmentStatus, startDate);
    }

    public void addAppointment(Appointment appointment
                              ) {
        appointments.add(appointment);
    }

    public void cancelAppointment(String appointmentId
                                  ) {
        for (Appointment a : appointments) {
            if (a.getAppointmentId().equals(appointmentId)) {
                a.UpdateStatus("Cancelled");
                return;
            }
        }
    }

    public void rescheduleAppointment(String appointmentId,
                                      String newDate,
                                      String newTime
                                      ) {
        for (Appointment a : appointments) {
            if (a.getAppointmentId().equals(appointmentId)) {
                a.setAppointmentDate(newDate);
                a.setAppointmentTime(newTime);
                return;
            }
        }
    }

    public Appointment viewAppointment(String appointmentId
                                       ) {
        for (Appointment a : appointments) {
            if (a.getAppointmentId().equals(appointmentId)) {
                return a;
            }
        }
        return null;
    }

    public void addReferral(Referral referral
                            ) {
        referrals.add(referral);
    }

    public Referral createReferral(String referralId,
                                   String patientId,
                                   String referredToClinicianId,
                                   String referredToFacilityId,
                                   String referralDate,
                                   String urgencyLevel,
                                   String referralReason,
                                   String clinicalSummary,
                                   String requestedInvestigations,
                                   String appointmentId,
                                   String createdDate) {

        return new Referral(
                referralId,
                patientId,
                this.getClinicianId(),
                referredToClinicianId,
                this.getWorkplaceId(),
                referredToFacilityId,
                referralDate,
                urgencyLevel,
                referralReason,
                clinicalSummary,
                requestedInvestigations,
                "New",
                appointmentId,
                "",
                createdDate,
                createdDate
        );
    }

    public void cancelReferral(String referralId,
                               String lastUpdated) {
        for (Referral r : referrals) {
            if (r.getReferralId().equals(referralId)) {
                r.setStatus("Cancelled");
                r.setLastUpdated(lastUpdated);
                return;
            }
        }
    }
}
