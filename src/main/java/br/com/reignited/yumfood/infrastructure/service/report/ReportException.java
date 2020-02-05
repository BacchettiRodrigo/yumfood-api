package br.com.reignited.yumfood.infrastructure.service.report;

public class ReportException extends RuntimeException {

    private static final long serialVesionUID = 1L;

    public ReportException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReportException(String message) {
        super(message);
    }
}
