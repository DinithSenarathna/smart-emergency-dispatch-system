package model;

public class Emergency {
    private EmergencyType type;
    private int location;
    private int severity;
    private long reportTime;

    public Emergency(EmergencyType type, int location, int severity, long reportTime) {
        this.type = type;
        this.location = location;
        this.severity = severity;
        this.reportTime = reportTime;
    }

    public EmergencyType getType() { return type; }
    public int getLocation() { return location; }
    public int getSeverity() { return severity; }
    public long getReportTime() { return reportTime; }
}
