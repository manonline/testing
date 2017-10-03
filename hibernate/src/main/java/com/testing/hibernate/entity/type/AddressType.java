package com.testing.hibernate.entity.type;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.HibernateException;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class AddressType implements UserType,Serializable {
    private String homeAddr;
    private String workAddr;

    //private static final char SPLITTER  = ';';
    private static int[] SQL_TYPES = new int[] {Types.VARCHAR, Types.VARCHAR};

    // column type
    public int[] sqlTypes() {
        return SQL_TYPES;
    }

    // field type
    public Class returnedClass() {
        return AddressType.class;
    }

    public Object nullSafeGet(ResultSet rs, String[] names, Object owner) throws HibernateException, SQLException {

        if (rs.wasNull()) {
            return null;
        }
        String homeAddr = rs.getString(names[0]);
        String workAddr = rs.getString(names[1]);

        AddressType address = new AddressType();
        address.setHomeAddr(homeAddr);
        address.setWorkAddr(workAddr);

        return address;
    }

    public void nullSafeSet(PreparedStatement st, Object value, int index) throws HibernateException, SQLException {
        AddressType address = (AddressType) value;
        if (value == null) {
            st.setNull(index, Types.VARCHAR);
            st.setNull(index+1, Types.VARCHAR);
        } else {
            st.setString(index, address.getHomeAddr());
            st.setString(index + 1, address.getWorkAddr());
        }
        System.out.println("Data has been saved! ");
    }

    // Hibernate can somehow optimize the code if isMutable is true
    public boolean isMutable() {
        return false;
    }

    public Object deepCopy(Object value) throws HibernateException {
        return value;
    }

    public boolean equals(Object x, Object y) throws HibernateException {
        if (x == y)
            return true;
        if (x == null || y == null)
            return false;
        AddressType add1 = (AddressType) x;
        AddressType add2 = (AddressType) y;
        return new EqualsBuilder() //使用EqualsBuilder类来方便地进行比对
                .append(add1.getHomeAddr(), add2.getHomeAddr()).append(
                        add2.getWorkAddr(), add2.getWorkAddr()).isEquals();
    }

    public int hashCode(Object x) throws HibernateException {
        AddressType address = (AddressType) x;
        return new HashCodeBuilder()//使用HashCodeBuilder类来方便地进行比对
                .append(address.getHomeAddr()).append(address.getWorkAddr())
                .toHashCode();
    }

    // for 2nd level cache
    public Serializable disassemble(Object value) throws HibernateException {
        return null;
    }

    // for 2nd level cache
    public Object assemble(Serializable cached, Object owner) throws HibernateException {
        return null;
    }

    // for 2nd level cache
    public Object replace(Object original, Object target, Object owner) throws HibernateException {
        return null;
    }

    /**
     * Object itself
     */
    public AddressType() {
        super();
    }

    public AddressType(String homeAddr, String workAddr) {
        super();
        this.homeAddr = homeAddr;
        this.workAddr = workAddr;
    }

    public static int[] getSqlTypes() {
        return SQL_TYPES;
    }

    public String getHomeAddr() {
        return homeAddr;
    }

    public String getWorkAddr() {
        return workAddr;
    }

    public void setHomeAddr(String homeAddr) {
        this.homeAddr = homeAddr;
    }

    public void setWorkAddr(String workAddr) {
        this.workAddr = workAddr;
    }
}
