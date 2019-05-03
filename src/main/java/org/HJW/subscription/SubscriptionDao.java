package org.HJW.subscription;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class SubscriptionDao {
	static final String ADD_SUBSCRIPTION = "INSERT subscription(userId,name,channelId,title) VALUES(?,?,?,?)";

	static final String DELETE_SUBSCRIPTION = "DELETE FROM subscription WHERE (userId, channelId)=(?,?)";

	static final String LIST_SUBSCRIPTIONS = "SELECT channelId,title FROM subscription WHERE userId=?";

	@Autowired
	JdbcTemplate jdbcTemplate;

	RowMapper<Subscription> subscriptionRowMapper = new BeanPropertyRowMapper<>(Subscription.class);

	public int addSubscription(Subscription subscription) {
		return jdbcTemplate.update(ADD_SUBSCRIPTION, subscription.getUserId(), subscription.getName(),
				subscription.getChannelId(), subscription.getTitle());
	}

	public int deleteSubscription(Subscription subscription) {
		return jdbcTemplate.update(DELETE_SUBSCRIPTION, subscription.getUserId(), subscription.getChannelId());
	}

	public List<Subscription> listSubscriptions(String userId) {
		return jdbcTemplate.query(LIST_SUBSCRIPTIONS, this.subscriptionRowMapper, userId);
	}
}
