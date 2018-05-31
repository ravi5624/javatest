package nst.app.lookups;

import nst.app.enums.UserType;
import nst.dto.LookupDataDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LookupUtil {

    public static List<LookupDataDTO> getExaminationCenters() {
        List<LookupDataDTO> data = new ArrayList<>();

        LookupDataDTO e1 = LookupDataDTO.create(1l, "SUPERVISOR_GENERAL", "Supervisor General");
        e1.addDependent(LookupDataDTO.create(1l, "Ahmedabad", "Ahmedabad"));
        e1.addDependent(LookupDataDTO.create(2l, "Vadodara", "Vadodara"));
        e1.addDependent(LookupDataDTO.create(3l, "Rajkot", "Rajkot"));
        e1.addDependent(LookupDataDTO.create(4l, "Bhavnagar", "Bhavnagar"));
        e1.addDependent(LookupDataDTO.create(5l, "Surat", "Surat"));
        data.add(e1);

        LookupDataDTO e2 = LookupDataDTO.create(2l, "SUPERVISOR_ORAL",
                "Supervisor Oral / Oral General (Age >45 Years)");
        e2.addDependent(LookupDataDTO.create(1l, "Ahmedabad", "Ahmedabad"));
        data.add(e2);

        LookupDataDTO e3 = LookupDataDTO
                .create(3l, "SUPERVISOR_MINES", "Supervisor Mines / Mines General");
        e3.addDependent(LookupDataDTO.create(1l, "Ahmedabad", "Ahmedabad"));
        data.add(e3);

        LookupDataDTO e4 = LookupDataDTO.create(4l, "MINES_EXEMPTION", "Mines Exemption");
        e4.addDependent(LookupDataDTO.create(1l, "Ahmedabad", "Ahmedabad"));
        data.add(e4);
        return data;
    }



    public static List<LookupDataDTO> examType(String userType) {
        List<LookupDataDTO> data = new ArrayList<>();

        if (userType.equals(UserType.SUPERVISOR.getType())) {
            data.add(LookupDataDTO.create(1l, "SUPERVISOR_GENERAL", "Supervisor General"));
            data.add(LookupDataDTO.create(2l, "SUPERVISOR_ORAL","Supervisor Oral / Oral General (Age >45 Years)"));
            data.add(LookupDataDTO.create(3l, "SUPERVISOR_MINES", "Supervisor Mines / Mines General"));
            data.add(LookupDataDTO.create(4l, "MINES_EXEMPTION", "Mines Exemption"));
        } else if(userType.equals(UserType.WIREMAN.getType())){
            data.add(LookupDataDTO.create(1l, "WIREMAN", "Wireman"));
        }

        return data;
    }

    public static List<LookupDataDTO> organizationType() {
        List<LookupDataDTO> data = new ArrayList<>();

        data.add(LookupDataDTO.create(1l, "PARTNERSHIP", "Partnership"));
        data.add(LookupDataDTO.create(2l, "PROPRIETORSHIP","Proprietorship"));
        data.add(LookupDataDTO.create(3l, "LLP", "LLP"));
        data.add(LookupDataDTO.create(4l, "PRIVATE", "Private Ltd."));
        data.add(LookupDataDTO.create(5l, "PUBLIC", "Public Ltd."));
        return data;
    }

    public static List<LookupDataDTO> agencyOrganizationType() {
        List<LookupDataDTO> data = new ArrayList<>();

        data.add(LookupDataDTO.create(1l, "PROPRIETORSHIP","Proprietorship"));
        data.add(LookupDataDTO.create(2l, "PARTNERSHIP", "Partnership"));
        data.add(LookupDataDTO.create(3l, "PUBLIC", "Public Ltd."));
        data.add(LookupDataDTO.create(4l, "PRIVATE", "Pvt Ltd."));
        return data;
    }


    public static List<LookupDataDTO> modeOfLanguage() {
        List<LookupDataDTO> data = new ArrayList<>();
        data.add(LookupDataDTO.create(1l, "ENG", "English"));
        data.add(LookupDataDTO.create(2l, "GUJ", "Gujarati"));
        data.add(LookupDataDTO.create(3l, "HIN", "Hindi"));
        return data;
    }
    public static List<LookupDataDTO> getDesignation() {
        List<LookupDataDTO> data = new ArrayList<>();
        data.add(LookupDataDTO.create(1l, "SUPERVISOR", "Supervisor"));
        data.add(LookupDataDTO.create(2l, "WIREMAN","Wireman"));
        data.add(LookupDataDTO.create(3l, "APPRENTICE", "Apprentice"));
        return data;
    }

    public static List<LookupDataDTO> getExperience(String examType) {
        List<LookupDataDTO> data = new ArrayList<>();
        if(examType.equals("SUPERVISOR_GENERAL"))
        {
            data.add(LookupDataDTO.create(1l, "E11", "4 Years Practical Experience Out Of Which Minimum 02 Years Experience After Getting Wireman Permit"));
            data.add(LookupDataDTO.create(2l, "E12","Three And Half Year Practical Experience Out Of Which Minimum One And Half Year Experience After Getting Wireman Permit (Who Has Completed Electrician Classes At Technical Institute And Passed Examination Of T.E.B.)"));
            data.add(LookupDataDTO.create(3l, "E13", "Period Of Apprenticeship Will Be Considered After Getting Wireman Certificate And Permit"));
        }
        else if(examType.equals("SUPERVISOR_ORAL"))
        {
            data.add(LookupDataDTO.create(1l, "E11", "4 Years Practical Experience Out Of Which Minimum 02 Years Experience After Getting Wireman Permit"));
            data.add(LookupDataDTO.create(2l, "E12","Three And Half Year Practical Experience Out Of Which Minimum One And Half Year Experience After Getting Wireman Permit (Who Has Completed Electrician Classes At Technical Institute And Passed Examination Of T.E.B.)"));
            data.add(LookupDataDTO.create(3l, "E13", "Period Of Apprenticeship Will Be Considered After Getting Wireman Certificate And Permit"));
        }
        else if(examType.equals("SUPERVISOR_MINES"))
        {
            data.add(LookupDataDTO.create(1l, "E11", "4 Years Practical Experience Out Of Which Minimum 02 Years Experience After Getting Wireman Permit And Experience In Mines Related Work Order"));
        }
        else if(examType.equals("MINES_EXEMPTION"))
        {
            data.add(LookupDataDTO.create(1l, "E11", "4 Years Practical Experience Out Of Which Minimum 02 Years Experience After Getting Wireman Permit 1 Year Experience In Mines Related Work"));
        }

        return data;
    }


    public static List<LookupDataDTO> getVehiclePosessions() {
        List<LookupDataDTO> data = new ArrayList<>();
        data.add(LookupDataDTO.create(1l, "OWNED", "Owned"));
        data.add(LookupDataDTO.create(2l, "RENTED", "Rented"));
        return data;
    }

    public static List<LookupDataDTO> getLegalStatus() {
        List<LookupDataDTO> data = new ArrayList<>();
        LookupDataDTO e1 = LookupDataDTO.create(1l, "PUBLIC LTD", "Public Ltd");
        e1.addDependent(LookupDataDTO.create(2l, "PROPERITERSHIP", "Properitorship"));
        e1.addDependent(LookupDataDTO.create(3l, "PARTNRSHIP", "Partnership"));
        data.add(e1);
        return data;
    }

    public  static List<LookupDataDTO> wiremanExamEligibility(){
        List<LookupDataDTO> data = new ArrayList<>();
        data.add(LookupDataDTO.create(1l,"OYEWEIUECL","1 Year Experience In Wiring And Electrical Installation Under Ele. Contractor Licence"));
        data.add(LookupDataDTO.create(2l,"WACTIPESMPE","Wireman Apprentice Classes At Technical Institute And Passed The Examination By T.e.b.+ Six Months Practical Experience"));
        data.add(LookupDataDTO.create(3l,"OYCECCI","One Year Course Of Electrician Conducted By Cottage Industries"));
        data.add(LookupDataDTO.create(4l,"OYCRMCCI","One Year Course Of Radio Mechanic Conducted By Cottage Industries"));
        data.add(LookupDataDTO.create(5l,"OYCWMRCCI","One Year Course Of Wireman Motor Rewinding Conducted By Cottage Industries"));
        data.add(LookupDataDTO.create(6l,"OYCECPDTCR","One Year Course Of Electrician Conducted By Prototype Development And Training Center Rajkot"));
        data.add(LookupDataDTO.create(7l,"SMCMW","Six Months Course Of Motor Winding"));
        data.add(LookupDataDTO.create(8l,"STE","State Trade In (Electrician)"));
        data.add(LookupDataDTO.create(9l,"STW","State Trade In (Wireman)"));
        return data;
    }

    public  static List<LookupDataDTO> getExaminationCenters(String userType, String examType) {
        List<LookupDataDTO> data = new ArrayList<>();
        if (userType == UserType.WIREMAN.getType()) {
            data.add(LookupDataDTO.create(1l, "Ahmedabad", "Ahmedabad"));
            data.add(LookupDataDTO.create(2l, "Vadodara", "Vadodara"));
            data.add(LookupDataDTO.create(3l, "Rajkot", "Rajkot"));
            data.add(LookupDataDTO.create(4l, "Bhavnagar", "Bhavnagar"));
            data.add(LookupDataDTO.create(5l, "Surat", "Surat"));
            data.add(LookupDataDTO.create(6l, "Junagadh", "Junagadh"));
            data.add(LookupDataDTO.create(7l, "Bhuj", "Bhuj"));
            data.add(LookupDataDTO.create(8l, "Valsad", "Valsad"));

            LocalDate today = LocalDate.now();
            int monthVal = today.getMonthValue();

            if (monthVal == 12 || (monthVal >= 1 && monthVal <= 5)) {
                data.add(LookupDataDTO.create(9l, "Mehsana", "Mehsana"));
            } else {
                data.add(LookupDataDTO.create(10l, "Patan", "Patan"));
            }
        } else if (userType == UserType.SUPERVISOR.getType()) {
            if (examType.equals("SUPERVISOR_GENERAL")) {
                data.add(LookupDataDTO.create(1l, "Ahmedabad", "Ahmedabad"));
                data.add(LookupDataDTO.create(2l, "Vadodara", "Vadodara"));
                data.add(LookupDataDTO.create(3l, "Rajkot", "Rajkot"));
                data.add(LookupDataDTO.create(4l, "Bhavnagar", "Bhavnagar"));
                data.add(LookupDataDTO.create(5l, "Surat", "Surat"));
            } else if (examType.equals("SUPERVISOR_ORAL") || examType.equals("SUPERVISOR_MINES") || examType.equals("MINES_EXEMPTION")) {
                data.add(LookupDataDTO.create(1l, "Ahmedabad", "Ahmedabad"));
            }

        }
        return data;
    }
    public static List<LookupDataDTO> getTechQualificationForSupervisor() {
        List<LookupDataDTO> data = new ArrayList<>();
        //For GUJARAT
        LookupDataDTO guj = LookupDataDTO.create(1l, "GUJARAT", "Gujarat");
        guj.addDependent(LookupDataDTO.create(1l, "DIPLOMA_IN_ELECTRICAL", "Diploma In Electrical Engineering"));
        guj.addDependent(LookupDataDTO.create(2l, "DEGREE_IN_ELECTRICAL","Degree In Electrical Engineering"));
        guj.addDependent(LookupDataDTO.create(3l, "DIPLOMA_IN_ELECTRICAL_ELECTRONICS", "Diploma In Electrical & Electronics Engineering"));
        guj.addDependent(LookupDataDTO.create(4l, "DEGREE_IN_ELECTRICAL_ELECTRONICS", "Degree In Electrical & Electronics Engineering"));
        guj.addDependent(LookupDataDTO.create(5l, "ELECTRICIAN_NCVT", "Electrician (Ncvt) + National Apprenticeship Certificate In Electrician + 1 Year Practical Experience"));
        guj.addDependent(LookupDataDTO.create(6l, "ELECTRIC_SERVICE_TECHNICIAN", "Electric Service Technician + 2 Years Practical Experience"));
        data.add(guj);

        //For Other
        LookupDataDTO other = LookupDataDTO.create(2l, "OTHER", "Other");
        other.addDependent(LookupDataDTO.create(1l, "DIPLOMA_IN_ELECTRICAL_ENGINEERING", "Diploma In Electrical Engineering"));
        other.addDependent(LookupDataDTO.create(2l, "BE_BTECH_IN_ELECTRICAL","B.e/B.tech In Electrical Engineering"));
        other.addDependent(LookupDataDTO.create(3l, "DIPLOMA_IN_ELECTRICAL_ELECTRONICS_ENGINEERING", "Diploma In Electrical & Electronics Engineering"));
        other.addDependent(LookupDataDTO.create(4l, "BE_BTECH_IN_ELECTRICAL_ELECTRONICS", "B.e/B.tech In Electrical & Electronics Engineering"));
        other.addDependent(LookupDataDTO.create(5l, "ANY_OTHER_QUALIFICATIONS", "Any Other Qualifications Equivalent To Degree/diploma In Electrical Engineering"));
        data.add(other);

        return data;
    }
    public static List<LookupDataDTO> getTechQualificationForWireman() {
        List<LookupDataDTO> data = new ArrayList<>();
        //For GUJARAT
        LookupDataDTO guj = LookupDataDTO.create(1l, "GUJARAT", "Gujarat");
        guj.addDependent(LookupDataDTO.create(1l, "GNACE", "National Apprenticeship Certificate (Electrician)"));
        guj.addDependent(LookupDataDTO.create(2l, "GNACW","National Apprenticeship Certificate (Wireman)"));
        guj.addDependent(LookupDataDTO.create(3l, "GNACL", "National Apprenticeship Certificate (Lineman)"));
        guj.addDependent(LookupDataDTO.create(4l, "GNTCE", "National Trade Certificate (Electrician)"));
        guj.addDependent(LookupDataDTO.create(5l, "GNTCW", "National Trade Certificate (Wireman)"));
        guj.addDependent(LookupDataDTO.create(6l, "GNTCL", "National Trade Certificate (Lineman)"));
        guj.addDependent(LookupDataDTO.create(7l, "GEST", "Electrical Service Technician"));
        guj.addDependent(LookupDataDTO.create(8l, "GEIWJ", "Electrical Installation,wiring And Jointing From Technical Examination Board"));
        data.add(guj);

        //For Other
        LookupDataDTO other = LookupDataDTO.create(2l, "OTHER", "Other");
        other.addDependent(LookupDataDTO.create(1l, "ONTCE", "National Trade Certificate (Electrician)"));
        other.addDependent(LookupDataDTO.create(2l, "ONTCW","National Trade Certificate (Wireman)"));
        other.addDependent(LookupDataDTO.create(3l, "ONTCL","National Trade Certificate (Lineman)"));
        other.addDependent(LookupDataDTO.create(4l, "ONACE", "National Apprenticeship Certificate (Electrician)"));
        other.addDependent(LookupDataDTO.create(5l, "ONACW", "National Apprenticeship Certificate (Wireman)"));
        other.addDependent(LookupDataDTO.create(6l, "ONACL", "National Apprenticeship Certificate (Lineman)"));
        other.addDependent(LookupDataDTO.create(7l, "OAOQ", "Any Other Qualifications Equivalent National Trade Or Apprenticeship Certificate"));
        data.add(other);

        return data;
    }

    public  static List<LookupDataDTO> getQualifications(){
        List<LookupDataDTO> data = new ArrayList<>();
        data.add(LookupDataDTO.create(1l,"BE_ME","Bachelor of Technology (Mechanical Engineering)"));
        data.add(LookupDataDTO.create(2l,"BE_EE","Bachelor of Technology (Electrical Engineering)"));
        data.add(LookupDataDTO.create(3l,"DIPLOMA_ME","Diploma In Mechanical Engineering"));
        data.add(LookupDataDTO.create(4l,"DIPLOMA_EE","Diploma In Electrical Engineering"));
        data.add(LookupDataDTO.create(5l,"ITI_ME","Industrial Training In Mechanical Engineering"));
        data.add(LookupDataDTO.create(6l,"ITI_EE","Industrial Training In Electrical Engineering"));
        return data;
    }

    public  static List<LookupDataDTO> getOccupancyRights(){
        List<LookupDataDTO> data = new ArrayList<>();
        data.add(LookupDataDTO.create(1l,"OWNED","Owned"));
        data.add(LookupDataDTO.create(2l,"RENTED","Rented"));
        return data;
    }


    public  static List<LookupDataDTO> getLiftApplication(){
        List<LookupDataDTO> data = new ArrayList<>();
        data.add(LookupDataDTO.create(1l,"NEW","New Lift Application"));
        data.add(LookupDataDTO.create(2l,"REPLACEMENT","Replacement Lift Application"));
        data.add(LookupDataDTO.create(3l,"ALTERATION","Alteration Lift Application"));
        return data;
    }

    public  static List<LookupDataDTO> getLiftTypes(){
        List<LookupDataDTO> data = new ArrayList<>();
        data.add(LookupDataDTO.create(1l,"PASSENGER","Passenger Based Lift"));
        data.add(LookupDataDTO.create(2l,"GOODS","Goods Based Lift"));
        data.add(LookupDataDTO.create(3l,"SERVICE","Service Based Lift"));
        data.add(LookupDataDTO.create(4l,"HOME","Home Based Lift"));
        data.add(LookupDataDTO.create(5l,"OTHER","Other Types of Lift"));
        return data;
    }

    public static List<LookupDataDTO> getSubCategoryOfLifts(){
        List<LookupDataDTO> data = new ArrayList<>();
        data.add(LookupDataDTO.create(1l,"TRACTION_WITH_MACHINE_ROOM","Traction with machine Room Sub-Category Lift"));
        data.add(LookupDataDTO.create(2l,"Traction_WITHOUT_MACHINE_ROOM","Traction Without Machine Room Sub-Category Lift"));
        data.add(LookupDataDTO.create(3l,"HYDRAULIC_LIFT","Hydraulic Sub-Category Lift"));
        data.add(LookupDataDTO.create(4l,"OTHER","Other's Sub-Category Lift"));
        return data;
    }

    public static List<LookupDataDTO> getControlLiftMethods(){
        List<LookupDataDTO> data = new ArrayList<>();
        data.add(LookupDataDTO.create(1L,"SINGLE_SPEED _ALTERNATING_CURRENT","Single-Speed Alternating Current Control Lift Method"));
        data.add(LookupDataDTO.create(2L,"TWO-SPEED_ALTERNATING_CURRENT","Two-Speed Alternating Current Control Lift Method"));
        data.add(LookupDataDTO.create(3L,"RHEOSTATIC","Rheostatic Control v"));
        data.add(LookupDataDTO.create(4L,"VARIABLE_VOLTAGE_MOTOR","Variable- Voltage Motor Control (Generator-Field Control) Lift Method"));
        data.add(LookupDataDTO.create(5L,"ELECTRONIC_DEVICES","Electronic Devices Control Lift Method"));
        data.add(LookupDataDTO.create(6L,"ALTERNATING_CURRENT_VARIABLE_VOLTAGE","Alternating Current Variable Voltage (ACVV) Control Lift Method"));
        data.add(LookupDataDTO.create(7L,"ALTERNATING_CURRENT_VARIABLE_VOLTAGE_VARIABLE_FREQUENCY","Alternating Current Variable Voltage Variable Frequency (ACVVVF) Control Lift Method"));
        data.add(LookupDataDTO.create(8L,"SOLID_STATE_D.C_VARIABLE_VOLTAGE","Solid-State d. c. Variable Voltage Control Lift Method"));
        return data;
    }

    public static List<LookupDataDTO> getCarDoorDetails(){
        List<LookupDataDTO> data = new ArrayList<>();
        data.add(LookupDataDTO.create(1L,"MANUAL","Manual Car Door"));
        data.add(LookupDataDTO.create(2L,"AUTOMATIC","Automatic Car Door"));
        return data;
    }

    public static List<LookupDataDTO> getLandingDoorDetails(){
        List<LookupDataDTO> data = new ArrayList<>();
        data.add(LookupDataDTO.create(1L,"IMPERFORATED","imperforated Landing Door"));
        data.add(LookupDataDTO.create(2L,"COLLAPSIBLE","Collapsible Landing Door"));
        data.add(LookupDataDTO.create(3L,"SWING_DOOR","Swing door Landing Door"));
        data.add(LookupDataDTO.create(4L,"SWING_DOOR_WITH_VISION_PANEL","Swing door with vision panel Landing Door"));

        /* if automatic   */
        data.add(LookupDataDTO.create(1L,"CENTER_OPENING","Centre Opening Landing Door"));
        data.add(LookupDataDTO.create(2L,"SIDE_OPENING","Side Opening Landing Door"));

        return data;
    }

    public static List<LookupDataDTO> getCounterWeightPositions(){
        List<LookupDataDTO> data = new ArrayList<>();
        data.add(LookupDataDTO.create(1L,"BACK_SIDE","Back Side Counter Weight Position"));
        data.add(LookupDataDTO.create(2L,"LEFT_SIDE","Left Side Counter Weight Position"));
        data.add(LookupDataDTO.create(3L,"RIGHT_SIDE","Right Side Counter Weight Position"));
        return data;
    }


    public static List<LookupDataDTO> getCarBufferTypes(){
        List<LookupDataDTO> data = new ArrayList<>();
        data.add(LookupDataDTO.create(1L,"SPRING","Spring Car Buffer Type"));
        data.add(LookupDataDTO.create(2L,"OIL","Oil Car Buffer Type"));
        data.add(LookupDataDTO.create(3L,"OTHER","Other Car Buffer Type"));
        return data;
    }

    public static List<LookupDataDTO> getGenderOfVictim(){
        List<LookupDataDTO> data = new ArrayList<>();
        data.add(LookupDataDTO.create(1L,"MALE","Male"));
        data.add(LookupDataDTO.create(2L,"FEMALE","Female"));
        data.add(LookupDataDTO.create(3L,"OTHER","Other"));
        return data;
    }
    public static List<LookupDataDTO> getTypeOfAccident(){
        List<LookupDataDTO> data = new ArrayList<>();
        data.add(LookupDataDTO.create(1L,"FATAL","Fatal"));
        data.add(LookupDataDTO.create(2L,"NON_FATAL","Non Fatal"));
        return data;
    }


    public static List<LookupDataDTO> getEmployee(){
        List<LookupDataDTO> data = new ArrayList<>();
        data.add(LookupDataDTO.create(1L,"BE_MECH","BE (Mech)"));
        data.add(LookupDataDTO.create(2L,"DIPLOMA_MECH","Diploma (Mech)"));
        data.add(LookupDataDTO.create(3L,"BE_ELECT","BE (Elect)"));
        data.add(LookupDataDTO.create(4L,"DIPLOMA_ELECT","Diploma (Elect)"));
        data.add(LookupDataDTO.create(5L,"ITI_ELECTRICAL","ITI (Electical)"));
        data.add(LookupDataDTO.create(6L,"ITI_ELECTRONICS","ITI (Electronics)"));
        data.add(LookupDataDTO.create(7L,"ELECTRIICIAN","Electrician"));
        data.add(LookupDataDTO.create(8L,"FITTER","Fitter"));
        data.add(LookupDataDTO.create(9L,"HELPER","Helper"));
        data.add(LookupDataDTO.create(10L,"CLERK","Clerk"));
        return data;
    }

    public static List<LookupDataDTO> getMachineries(){
        List<LookupDataDTO> data = new ArrayList<>();
        data.add(LookupDataDTO.create(1L,"CLAMPON_METER","Clampon meter"));
        data.add(LookupDataDTO.create(2L,"MULTI_METER","Multi meter"));
        data.add(LookupDataDTO.create(3L,"TACHO_METER","Tacho meter"));
        data.add(LookupDataDTO.create(4L,"PORTABLE_DRILL_MACHINE","Portable drill machine"));
       return data;
    }
    public static List<LookupDataDTO> getSafetyGadgets(){
        List<LookupDataDTO> data = new ArrayList<>();
        data.add(LookupDataDTO.create(1L,"SAFETY_HELMETS","Safety helmets"));
        data.add(LookupDataDTO.create(2L,"SAFETY_SHOES","Safety shoes"));
        data.add(LookupDataDTO.create(3L,"AFETY_BELTS","Safety belts"));
        data.add(LookupDataDTO.create(4L,"SAFETY_GLOVES","Safety gloves"));
        return data;
    }
    public static List<LookupDataDTO> getStaffDetails(){
        List<LookupDataDTO> data = new ArrayList<>();
        data.add(LookupDataDTO.create(1L,"BE_MECH","BE (Mech)"));
        data.add(LookupDataDTO.create(2L,"BE_ELECT","BE (Elect)"));
        data.add(LookupDataDTO.create(3L,"ITI_ELECTRICAL","ITI (Electical)"));
        data.add(LookupDataDTO.create(4L,"ELECTRIICIAN","Electrician"));
        data.add(LookupDataDTO.create(5L,"FITTER","Fitter"));
        data.add(LookupDataDTO.create(6L,"HELPER","Helper"));
        data.add(LookupDataDTO.create(7L,"CLERK","Clerk"));

        return data;
    }

}
