package com.pusilkom.hris.model;

import java.io.Serializable;
import java.util.Date;

public class AuthAssignment implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.auth_assignment.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.auth_assignment.item_name
     *
     * @mbg.generated
     */
    private String itemName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.auth_assignment.user_id
     *
     * @mbg.generated
     */
    private Long userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.auth_assignment.user_create
     *
     * @mbg.generated
     */
    private String userCreate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column public.auth_assignment.timestamp_create
     *
     * @mbg.generated
     */
    private Date timestampCreate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table public.auth_assignment
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.auth_assignment.id
     *
     * @return the value of public.auth_assignment.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.auth_assignment.id
     *
     * @param id the value for public.auth_assignment.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.auth_assignment.item_name
     *
     * @return the value of public.auth_assignment.item_name
     *
     * @mbg.generated
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.auth_assignment.item_name
     *
     * @param itemName the value for public.auth_assignment.item_name
     *
     * @mbg.generated
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.auth_assignment.user_id
     *
     * @return the value of public.auth_assignment.user_id
     *
     * @mbg.generated
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.auth_assignment.user_id
     *
     * @param userId the value for public.auth_assignment.user_id
     *
     * @mbg.generated
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.auth_assignment.user_create
     *
     * @return the value of public.auth_assignment.user_create
     *
     * @mbg.generated
     */
    public String getUserCreate() {
        return userCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.auth_assignment.user_create
     *
     * @param userCreate the value for public.auth_assignment.user_create
     *
     * @mbg.generated
     */
    public void setUserCreate(String userCreate) {
        this.userCreate = userCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column public.auth_assignment.timestamp_create
     *
     * @return the value of public.auth_assignment.timestamp_create
     *
     * @mbg.generated
     */
    public Date getTimestampCreate() {
        return timestampCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column public.auth_assignment.timestamp_create
     *
     * @param timestampCreate the value for public.auth_assignment.timestamp_create
     *
     * @mbg.generated
     */
    public void setTimestampCreate(Date timestampCreate) {
        this.timestampCreate = timestampCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.auth_assignment
     *
     * @mbg.generated
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        AuthAssignment other = (AuthAssignment) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getItemName() == null ? other.getItemName() == null : this.getItemName().equals(other.getItemName()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getUserCreate() == null ? other.getUserCreate() == null : this.getUserCreate().equals(other.getUserCreate()))
            && (this.getTimestampCreate() == null ? other.getTimestampCreate() == null : this.getTimestampCreate().equals(other.getTimestampCreate()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table public.auth_assignment
     *
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getItemName() == null) ? 0 : getItemName().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getUserCreate() == null) ? 0 : getUserCreate().hashCode());
        result = prime * result + ((getTimestampCreate() == null) ? 0 : getTimestampCreate().hashCode());
        return result;
    }
}