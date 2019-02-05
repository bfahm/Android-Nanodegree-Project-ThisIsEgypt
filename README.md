
# ThisIsEgypt - Tour Guide App
### 5th Project in Udacity's Android Basics Nanoderee
### Lesson: Multi-screen Apps
-----

**Introduction**

The application consists of one main activity, multiple fragments for each city.

 - Each location in any city has:
	 - Title
	 - Subtitle
	 - Rating gained from Google Places
	 - Wikipedia Page under the button (More Info Button)
	 - Could be liked/unliked (Just a switching icon for now..)
	 - Button to jump to Google Maps Location
	 - Button to open a local browser searching for the place's pictures.
 - The app utilizes `RecylcerView` instead of regular `ListView`, which integrates well with the `CoordinatorView` needed for `CollapsingToolBar`. It also helps in integrating multiple onClickListeners for various buttons and views within one recylcable item.
 - The app also utilized `TabLayout` and `ViewPager` to be able to view all fragments within the main activity.
-----
![image](https://drive.google.com/uc?export=view&id=1AAzmZJBKsmu-o0o4h04S7NeLETnfdZym)
![image](https://drive.google.com/uc?export=view&id=1BXAijX8mtAzETHd73C4HPSfa2HpRdjJX)