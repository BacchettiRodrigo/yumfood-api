package br.com.reignited.yumfood.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Problem {
    private Integer status;
    private String type;
    private String title;
    private String detail;
    private String userMessage;
    private OffsetDateTime timestamp;
    private List<Object> objects;


    private Problem(ProblemBuilder problemBuilder) {
        this.status = problemBuilder.status;
        this.type = problemBuilder.type;
        this.title = problemBuilder.title;
        this.detail = problemBuilder.detail;
        this.userMessage = problemBuilder.userMessage;
        this.timestamp = problemBuilder.timestamp;
        this.objects = problemBuilder.objects;
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

    public OffsetDateTime getTimestamp() {
        return timestamp;
    }

    public List<Object> getObjects() {
        return objects;
    }

    public static class Object {

        private String name;
        private String userMessage;

        Object(ObjectBuilder objectBuilder) {
            this.name = objectBuilder.name;
            this.userMessage = objectBuilder.userMessage;
        }

        public String getName() {
            return name;
        }

        public String getUserMessage() {
            return userMessage;
        }

        public static ObjectBuilder builder() {
            return new ObjectBuilder();
        }

        public static class ObjectBuilder {

            private String name;
            private String userMessage;

            ObjectBuilder() {
            }

            ObjectBuilder name(String name) {
                this.name = name;
                return this;
            }

            ObjectBuilder userMessage(String userMessage) {
                this.userMessage = userMessage;
                return this;
            }

            Object build() {
                return new Object(this);
            }
        }
    }

    public static class ProblemBuilder {
        private Integer status;
        private String type;
        private String title;
        private String detail;
        private String userMessage;
        private OffsetDateTime timestamp;
        private List<Object> objects;

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

        ProblemBuilder userMessage(String userMessage) {
            this.userMessage = userMessage;
            return this;
        }

        ProblemBuilder timestamp(OffsetDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        ProblemBuilder objects(List<Object> objects) {
            this.objects = objects;
            return this;
        }

        Problem build() {
            return new Problem(this);
        }
    }
}
