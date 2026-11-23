package isep.lab1.bean;

import java.io.Serializable;

/**
 * 2.2 : JavaBean utilise par bean.jsp.
 * Regles d'un JavaBean : constructeur public sans argument, proprietes privees,
 * getters/setters publics, Serializable.
 * Les noms des proprietes sont identiques aux noms des champs du formulaire,
 * ce qui permet &lt;jsp:setProperty property="*"/&gt;.
 */
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    private String firstName;
    private String lastName;
    private String birthDay;
    private String sex;

    public Person() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
