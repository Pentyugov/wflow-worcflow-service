package com.pentyugov.wflow.workflowservice.core.domain;

import com.pentyugov.wflow.workflowservice.core.system.annotation.SoftDelete;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@SoftDelete
public class BaseEntity implements Serializable {

    @Id
    private String id;

    @Field(name = "createDate")
    private LocalDateTime createDate;

    @Field(name = "updateDate")
    private LocalDateTime updateDate;

    @Field(name = "deleteDate")
    private LocalDateTime deleteDate;

    @Version
    @Field(name = "version")
    private Integer version;

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        return Objects.equals(getId(), ((BaseEntity) other).getId());
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }

}
