package app.request;

public class UpdatePasswordRequest
{
    private String password,newPassword;
    public String getPassword()
    {
        return password;
    }
    public void setPassword(String password)
    {
        this.password=password;
    }
    public String getNewPassword()
    {
        return newPassword;
    }
    public void setNewPassword(String newPassword)
    {
        this.newPassword=newPassword;
    }
}