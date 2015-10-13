package com.example.joakes.xbox_sidekick.presenters_old;

import android.test.AndroidTestCase;
import android.view.LayoutInflater;
import android.view.View;

import com.example.joakes.xbox_sidekick.R;
import com.example.joakes.xbox_sidekick.adapters.recylerview.AchievementViewHolder;
import com.example.joakes.xbox_sidekick.helpers.TestSetup;
import com.example.joakes.xbox_sidekick.models.Achievement;
import com.example.joakes.xbox_sidekick.requests.WebService;

import static org.assertj.android.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

/**
 * Created by joakes on 6/3/15.
 */
public class AchievementPresenterTest extends AndroidTestCase {
    private AchievementViewHolder holder;
    private Achievement achievement;
    private WebService webService;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        achievement = TestSetup.createAchievement();
        View v = LayoutInflater.from(getContext()).inflate(R.layout.achievement, null, false);
        holder = new AchievementViewHolder(v);
        webService = spy(WebService.class);
        doNothing().when(webService).loadImageFromUrl(holder.achievementImageview, achievement.getIconUrl());
    }

    public void testAchievementUnLocked() {
        achievement.setLocked(false);
        presentAchievement();
        assertThat(holder.lockImageView).isInvisible();
        assertThat(holder.achievementNameTextview).hasText(achievement.getName());
        assertThat(holder.achievementDescriptionTextview).hasText(achievement.getDescription());
        verifyAchievementValueDisplayed();
        verifyIconDisplayed();
        verifyIconNotGrayScale();
    }

    public void testAchievementLockedAndNotSecret() {
        achievement.setLocked(true);
        achievement.setIsSecret(false);
        presentAchievement();
        assertThat(holder.lockImageView).isVisible();
        assertThat(holder.achievementDescriptionTextview).hasText(achievement.getLockedDescription());
        verifyAchievementValueDisplayed();
        verifyIconDisplayed();
        verifyIconGrayScale();
    }

    public void testAchievementsLockedAndSecret() {
        achievement.setLocked(true);
        achievement.setIsSecret(true);
        presentAchievement();
        assertThat(holder.lockImageView).isVisible();
        assertThat(holder.achievementNameTextview).hasText(R.string.secret_achievement);
        assertThat(holder.achievementDescriptionTextview).hasText(R.string.secret_achievement_description);
        verifyAchievementValueDisplayed();
        verifyIconNotDisplayed();
    }

    private void presentAchievement() {
        new AchievementPresenter(achievement, webService).present(holder);
    }

    private void verifyAchievementValueDisplayed() {
        assertThat(holder.achievementScoreImageTextview).hasText("" + achievement.getValue());
    }

    private void verifyIconDisplayed() {
        verify(webService).loadImageFromUrl(holder.achievementImageview, achievement.getIconUrl());
    }

    private void verifyIconNotDisplayed() {
        verify(webService, never()).loadImageFromUrl(holder.achievementImageview, achievement.getIconUrl());
    }

    private void verifyIconNotGrayScale() {
        assertNull(holder.achievementImageview.getColorFilter());
    }

    private void verifyIconGrayScale() {
        assertNotNull(holder.achievementImageview.getColorFilter());
    }
}
