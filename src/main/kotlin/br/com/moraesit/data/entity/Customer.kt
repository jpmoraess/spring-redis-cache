package br.com.moraesit.data.entity

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Type
import org.hibernate.annotations.UpdateTimestamp
import java.io.Serializable
import java.time.OffsetDateTime
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Customer(
        @Id
        @GeneratedValue(generator = "UUID")
        @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
        @Type(type = "org.hibernate.type.UUIDCharType")
        @Column(length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false)
        val id: UUID? = null
) : Serializable {
    var name: String? = null
    var email: String? = null

    @Column(updatable = false)
    @CreationTimestamp
    var createdAt: OffsetDateTime? = null

    @UpdateTimestamp
    var updatedAt: OffsetDateTime? = null
}
