package br.com.reignited.yumfood.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Problem {
    private Integer status;
    private String type;
    private String title;
    private String detail;
    private String userMessage;
    private LocalDateTime timestamp;

    private Problem(ProblemBuilder problemBuilder) {
        this.status = problemBuilder.status;
        this.type = problemBuilder.type;
        this.title = problemBuilder.title;
        this.detail = problemBuilder.detail;
        this.userMessage = problemBuilder.userMessage;
        this.timestamp = problemBuilder.timestamp;
    }

    public static ProblemBuilder builder() {
        return new ProblemBuilder();
    }

    public Integer getStatus() {
        return status;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getDetail() {
        return detail;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public static class ProblemBuilder {
        private Integer status;
        private String type;
        private String title;
        private String detail;
        private String userMessage;
        private LocalDateTime timestamp;

        ProblemBuilder() {
        }

        ProblemBuilder status(Integer status) {
            this.status = status;
            return this;
        }

        ProblemBuilder type(String type) {
            this.type = type;
            return this;
        }

        ProblemBuilder title(String title) {
            this.title = title;
            return this;
        }

        ProblemBuilder detail(String detail) {
            this.detail = detail;
            return this;
        }

        public ProblemBuilder userMessage(String userMessage) {
            this.userMessage = userMessage;
            return this;
        }

        public ProblemBuilder timestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        Problem build() {
            return new Problem(this);
        }
    }
}
