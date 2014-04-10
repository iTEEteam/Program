package skeleton;

//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : RangeGem.java
//  @ Date : 2014.03.11.
//  @ Author : itee_team
//
//




public class RangeGem extends Gem implements ITGem {
	public static final int price = 20;

	private int range;
	public RangeGem() {
		SkeletonTester.safePrint("--> RangeGem konstruktor", true);

		SkeletonTester.safePrint("<-- RangeGem konstruktor return", false);
	}
	public void upgradeTower(Tower t) {
		SkeletonTester.safePrint("--> RangeGem upgradeTower", true);
		t.upgradeRange(range);
		SkeletonTester.safePrint("<-- RangeGem upgradeTower return", false);
	}
	
	public int getValue() {
		SkeletonTester.safePrint("--> RangeGem getValue", true);
		
		SkeletonTester.safePrint("<-- RangeGem return", false);
		return 0;
	}
}