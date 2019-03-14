package cn.com.dbridge.lifecare.framework.dto.mobile;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * t_user_task_real
 * @author 
 */
public class UserTaskPoolRealDTO implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 用户编号
     */
    private Integer userId;

    /**
     * 0：任务池 1:我的任务
     */
    private String type;

    /**
     * 订单编号
     */
    private List<Integer> taskIds;

    /**
     * 查看时间
     */
    private Date showTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public List<Integer> getTaskIds() {
		return taskIds;
	}

	public void setTaskIds(List<Integer> taskIds) {
		this.taskIds = taskIds;
	}

	public Date getShowTime() {
        return showTime;
    }

    public void setShowTime(Date showTime) {
        this.showTime = showTime;
    }

	@Override
	public int hashCode() {
		return Objects.hash(id, showTime, taskIds, type, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserTaskPoolRealDTO other = (UserTaskPoolRealDTO) obj;
		return Objects.equals(id, other.id) && Objects.equals(showTime, other.showTime)
				&& Objects.equals(taskIds, other.taskIds) && Objects.equals(type, other.type)
				&& Objects.equals(userId, other.userId);
	}

	@Override
	public String toString() {
		return "UserTaskPoolRealDTO [id=" + id + ", userId=" + userId + ", type=" + type + ", taskIds=" + taskIds
				+ ", showTime=" + showTime + "]";
	}

   

   
}