package virtech.com.polacore.model.domain;

import virtech.com.polacore.model.enums.Status;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @Enumerated(EnumType.STRING)
    private Status status;
    private LocalDateTime created;
    private LocalDateTime lastModified;

    public BaseEntity() {
        this.status = Status.ACTIVE;
    }

    public Long getId() {
        return this.id;
    }

    public Status getStatus() {
        return this.status;
    }

    public LocalDateTime getCreated() {
        return this.created;
    }
    public LocalDateTime getLastModified() {
        return this.lastModified;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setStatus(final Status status) {
        this.status = status;
    }

    public void setCreated(final LocalDateTime created) {
        this.created = created;
    }

    public void setLastModified(final LocalDateTime lastModified) {
        this.lastModified = lastModified;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof BaseEntity)) {
            return false;
        } else {
            BaseEntity other = (BaseEntity) o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$id = this.getId();
                Object other$id = other.getId();
                if (this$id == null) {
                    if (other$id != null) {
                        return false;
                    }
                } else if (!this$id.equals(other$id)) {
                    return false;
                }

                Object this$status = this.getStatus();
                Object other$status = other.getStatus();
                if (this$status == null) {
                    if (other$status != null) {
                        return false;
                    }
                } else if (!this$status.equals(other$status)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof BaseEntity;
    }

    public int hashCode() {
        //todo: estaba -> int PRIME = true; verificar con common lib.
        boolean PRIME = true;
        int result = 1;
        Object $id = this.getId();
        result = result * 59 + ($id == null ? 43 : $id.hashCode());
        Object $status = this.getStatus();
        result = result * 59 + ($status == null ? 43 : $status.hashCode());
        return result;
    }

    public String toString() {
        Long var10000 = this.getId();
        return "BaseEntity(id=" + var10000 + ", status=" + this.getStatus() + ")";
    }


}
