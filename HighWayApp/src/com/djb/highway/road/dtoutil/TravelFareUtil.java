package com.djb.highway.road.dtoutil;

public class TravelFareUtil {

	// 计算货车行驶总费用的函数
	public static double countTruckFare(int axletreeNo, double weight,
			double distance, int[] type) {

		double finalFare = 0;
		double baseRate = 0.09;
		double changeRate;
		double legalWeight;

		// 货车类型分析：

		if (type[0] == 0) {
			// 非优惠车辆（普通车辆）

			if (axletreeNo == 2) {
				// 合法装载重量
				legalWeight = 17;
				if (weight < 0 || weight > 9999) {
					// System.out.println("weight is illegal");
				} else {
					if (weight <= 5) {
						weight = 5;
						finalFare = baseRate * weight * distance;
					} else if (weight > 5 && weight <= legalWeight) {
						// 获取相应的费率
						// 基本费率0.09元/吨公里线性递减至0.066元/吨公里
						changeRate = getRate(weight, axletreeNo);
						finalFare = changeRate * weight * distance;
					} else if (weight > legalWeight
							&& weight <= legalWeight * 1.3) {
						// 未超限部分按合法装载车辆记重收费标准记收
						// 对超限0—30%(含30%)部分,按基本费率计收(0.09元/吨公里)
						finalFare = getRate(legalWeight, axletreeNo)
								* legalWeight * distance + baseRate
								* (weight - legalWeight) * distance;

					} else if (weight > legalWeight * 1.3
							&& weight <= legalWeight * 2) {
						// 未超限部分按合法装载车辆记重收费标准记收
						// 对超限30％—100％（含100%）部分，按基本费率的3倍线性递增至6倍计收
						changeRate = getRate(weight, axletreeNo);
						finalFare = getRate(legalWeight, axletreeNo)
								* legalWeight * distance + changeRate
								* (weight - legalWeight) * distance;

					} else if (weight > legalWeight * 2) {
						// 未超限部分按合法装载车辆记重收费标准记收
						// 对超限100％以上部分，按基本费率的6倍计收
						finalFare = getRate(legalWeight, axletreeNo)
								* legalWeight * distance + 6 * baseRate
								* (weight - legalWeight) * distance;
					}

				}

			} else if (axletreeNo == 3) {
				// 合法装载重量
				legalWeight = 27;
				if (weight < 0 || weight > 9999) {
					// System.out.println("weight is illegal");
				} else {
					if (weight <= 5) {
						weight = 5;
						finalFare = baseRate * weight * distance;
					} else if (weight > 5 && weight < 17) {
						// 获取相应的费率
						// 基本费率0.09元/吨公里线性递减至0.066元/吨公里
						changeRate = getRate(weight, axletreeNo);
						finalFare = changeRate * weight * distance;
					} else if (weight >= 17 && weight <= legalWeight) {
						// 获取相应的费率
						// 按基本费率0.09元/吨公里线性递减至0.0486元/吨公里计收
						changeRate = getRate(weight, axletreeNo);
						finalFare = changeRate * weight * distance;

					} else if (weight > legalWeight
							&& weight <= legalWeight * 1.3) {
						// 未超限部分按合法装载车辆记重收费标准记收
						// 对超限0—30%(含30%)部分,按基本费率计收(0.09元/吨公里)
						finalFare = getRate(legalWeight, axletreeNo)
								* legalWeight * distance + baseRate
								* (weight - legalWeight) * distance;

					} else if (weight > legalWeight * 1.3
							&& weight <= legalWeight * 2) {
						// 未超限部分按合法装载车辆记重收费标准记收
						// 对超限30％—100％（含100%）部分，按基本费率的3倍线性递增至6倍计收
						changeRate = getRate(weight, axletreeNo);
						finalFare = getRate(legalWeight, axletreeNo)
								* legalWeight * distance + changeRate
								* (weight - legalWeight) * distance;

					} else if (weight > legalWeight * 2) {
						// 未超限部分按合法装载车辆记重收费标准记收
						// 对超限100％以上部分，按基本费率的6倍计收
						finalFare = getRate(legalWeight, axletreeNo)
								* legalWeight * distance + 6 * baseRate
								* (weight - legalWeight) * distance;
					}

				}

			} else if (axletreeNo == 4) {
				// 合法装载重量
				legalWeight = 35;
				if (weight < 0 || weight > 9999) {
					// System.out.println("weight is illegal");
				} else {
					if (weight <= 5) {
						weight = 5;
						finalFare = baseRate * weight * distance;
					} else if (weight > 5 && weight <= legalWeight) {
						// 车货总质量大于5吨小于等于35吨的
						// 按基本费率0.09元/吨公里线性递减至0.0486元/吨公里计收
						changeRate = getRate(weight, axletreeNo);
						finalFare = changeRate * weight * distance;

					} else if (weight > legalWeight
							&& weight <= legalWeight * 1.3) {
						// 未超限部分按合法装载车辆记重收费标准记收
						// 对超限0—30%(含30%)部分,按基本费率计收(0.09元/吨公里)
						finalFare = getRate(legalWeight, axletreeNo)
								* legalWeight * distance + baseRate
								* (weight - legalWeight) * distance;

					} else if (weight > legalWeight * 1.3
							&& weight <= legalWeight * 2) {

						// 未超限部分按合法装载车辆记重收费标准记收
						// 对超限30％—100％（含100%）部分，按基本费率的3倍线性递增至6倍计收
						changeRate = getRate(weight, axletreeNo);
						finalFare = getRate(legalWeight, axletreeNo)
								* legalWeight * distance + changeRate
								* (weight - legalWeight) * distance;

					} else if (weight > legalWeight * 2) {
						// 未超限部分按合法装载车辆记重收费标准记收
						// 对超限100％以上部分，按基本费率的6倍计收
						finalFare = getRate(legalWeight, axletreeNo)
								* legalWeight * distance + 6 * baseRate
								* (weight - legalWeight) * distance;
					}

				}

			} else if (axletreeNo == 5) {
				// 合法装载重量
				legalWeight = 43;
				if (weight < 0 || weight > 9999) {
					// System.out.println("weight is illegal");
				} else {
					if (weight <= 5) {
						weight = 5;
						finalFare = baseRate * weight * distance;
					} else if (weight > 5 && weight <= 35) {
						// 车货总质量大于5吨小于等于35吨的
						// 按基本费率0.09元/吨公里线性递减至0.0486元/吨公里计收
						changeRate = getRate(weight, axletreeNo);
						finalFare = changeRate * weight * distance;

					} else if (weight > 35 && weight <= legalWeight) {
						// 车货总质量大于35吨小于等于49吨的
						// 0.0486元/吨公里非线性递减至0.0346元/吨公里
						changeRate = getRate(weight, axletreeNo);
						finalFare = changeRate * weight * distance;

					} else if (weight > legalWeight
							&& weight <= legalWeight * 1.3) {
						// 未超限部分按合法装载车辆记重收费标准记收
						// 对超限0—30%(含30%)部分,按基本费率计收(0.09元/吨公里)
						finalFare = getRate(legalWeight, axletreeNo)
								* legalWeight * distance + baseRate
								* (weight - legalWeight) * distance;

					} else if (weight > legalWeight * 1.3
							&& weight <= legalWeight * 2) {
						// 未超限部分按合法装载车辆记重收费标准记收
						// 对超限30％—100％（含100%）部分，按基本费率的3倍线性递增至6倍计收
						changeRate = getRate(weight, axletreeNo);
						finalFare = getRate(legalWeight, axletreeNo)
								* legalWeight * distance + changeRate
								* (weight - legalWeight) * distance;

					} else if (weight > legalWeight * 2) {
						// 未超限部分按合法装载车辆记重收费标准记收
						// 对超限100％以上部分，按基本费率的6倍计收
						finalFare = getRate(legalWeight, axletreeNo)
								* legalWeight * distance + 6 * baseRate
								* (weight - legalWeight) * distance;
					}

				}

			} else if (axletreeNo >= 6) {
				// 合法装载重量
				legalWeight = 49;
				if (weight < 0 || weight > 9999) {
					// System.out.println("weight is illegal");
				} else {
					if (weight <= 5) {
						weight = 5;
						finalFare = baseRate * weight * distance;
					} else if (weight > 5 && weight <= 35) {
						// 车货总质量大于5吨小于等于35吨的
						// 按基本费率0.09元/吨公里线性递减至0.0486元/吨公里计收
						changeRate = getRate(weight, axletreeNo);
						finalFare = changeRate * weight * distance;

					} else if (weight > 35 && weight <= legalWeight) {

						// 车货总质量大于35吨小于等于49吨的
						// 0.0486元/吨公里非线性递减至0.0346元/吨公里
						changeRate = getRate(weight, axletreeNo);
						finalFare = changeRate * weight * distance;

					} else if (weight > legalWeight
							&& weight <= legalWeight * 1.3) {

						// 未超限部分按合法装载车辆记重收费标准记收
						// 对超限0—30%(含30%)部分,按基本费率计收(0.09元/吨公里)
						finalFare = getRate(legalWeight, axletreeNo)
								* legalWeight * distance + baseRate
								* (weight - legalWeight) * distance;

					} else if (weight > legalWeight * 1.3
							&& weight <= legalWeight * 2) {

						// 未超限部分按合法装载车辆记重收费标准记收
						// 对超限30％—100％（含100%）部分，按基本费率的3倍线性递增至6倍计收
						changeRate = getRate(weight, axletreeNo);

						finalFare = getRate(legalWeight, axletreeNo)
								* legalWeight * distance + changeRate
								* (weight - legalWeight) * distance;

					} else if (weight > legalWeight * 2) {

						// 未超限部分按合法装载车辆记重收费标准记收
						// 对超限100％以上部分，按基本费率的6倍计收
						finalFare = getRate(legalWeight, axletreeNo)
								* legalWeight * distance + 6 * baseRate
								* (weight - legalWeight) * distance;
					}

				}

			}

		} else if (type[0] == 1) {
			// 优惠车辆：绿色通道车辆、运输油气等化学危险品标准罐车辆、运输国际标准集装箱车辆
			if (type[1] == 0) {
				// 绿色通道车辆
				// 优惠比率 favorableRate
				double favorableRate = 0.75;
				if (axletreeNo == 2) {
					// 合法装载重量
					legalWeight = 17;
					// 未超限车辆，按正常车辆收费标准的75%计收；
					// 超限车辆，未超限部分按普通车辆合法装载收费标准记收，超限部分按基本费率计收
					if (weight < 0 || weight > 9999) {
						// System.out.println("weight is illegal");
					} else {
						if (weight <= 5) {
							weight = 5;
							finalFare = baseRate * weight * distance
									* favorableRate;
						} else if (weight > 5 && weight <= legalWeight) {
							// 未超限车辆，按正常车辆收费标准的75%计收；
							changeRate = getRate(weight, axletreeNo);
							finalFare = changeRate * weight * distance
									* favorableRate;
						} else if (weight > legalWeight) {
							// 超限车辆，未超限部分按普通车辆合法装载收费标准记收，超限部分按基本费率计收
							finalFare = getRate(legalWeight, axletreeNo)
									* legalWeight * distance + baseRate
									* (weight - legalWeight) * distance;
						}
					}

				} else if (axletreeNo == 3) {
					// 合法装载重量
					legalWeight = 27;
					if (weight < 0 || weight > 9999) {
						// System.out.println("weight is illegal");
					} else {
						if (weight <= 5) {
							weight = 5;
							finalFare = baseRate * weight * distance
									* favorableRate;
						} else if (weight > 5 && weight < 17) {
							// 未超限车辆，按正常车辆收费标准的75%计收；
							changeRate = getRate(weight, axletreeNo);
							finalFare = changeRate * weight * distance
									* favorableRate;
						} else if (weight >= 17 && weight <= legalWeight) {
							// 未超限车辆，按正常车辆收费标准的75%计收；
							changeRate = getRate(weight, axletreeNo);
							finalFare = changeRate * weight * distance
									* favorableRate;

						} else if (weight > legalWeight) {
							// 超限车辆，未超限部分按普通车辆合法装载收费标准记收，超限部分按基本费率计收
							finalFare = getRate(legalWeight, axletreeNo)
									* legalWeight * distance + baseRate
									* (weight - legalWeight) * distance;

						}
					}

				} else if (axletreeNo == 4) {
					// 合法装载重量
					legalWeight = 35;
					if (weight < 0 || weight > 9999) {
						// System.out.println("weight is illegal");
					} else {
						if (weight <= 5) {
							weight = 5;
							finalFare = baseRate * weight * distance
									* favorableRate;
						} else if (weight > 5 && weight <= legalWeight) {
							// 未超限车辆，按正常车辆收费标准的75%计收；
							changeRate = getRate(weight, axletreeNo);
							finalFare = changeRate * weight * distance
									* favorableRate;

						} else if (weight > legalWeight) {
							// 超限车辆，未超限部分按普通车辆合法装载收费标准记收，超限部分按基本费率计收
							finalFare = getRate(legalWeight, axletreeNo)
									* legalWeight * distance + baseRate
									* (weight - legalWeight) * distance;
						}

					}

				} else if (axletreeNo == 5) {
					// 合法装载重量
					legalWeight = 43;
					if (weight < 0 || weight > 9999) {
						// System.out.println("weight is illegal");
					} else {
						if (weight <= 5) {
							weight = 5;
							finalFare = baseRate * weight * distance
									* favorableRate;
						} else if (weight > 5 && weight <= 35) {
							// 未超限车辆，按正常车辆收费标准的75%计收；
							changeRate = getRate(weight, axletreeNo);
							finalFare = changeRate * weight * distance
									* favorableRate;

						} else if (weight > 35 && weight <= legalWeight) {
							// 未超限车辆，按正常车辆收费标准的75%计收；
							changeRate = getRate(weight, axletreeNo);
							finalFare = changeRate * weight * distance
									* favorableRate;

						} else if (weight > legalWeight) {
							// 超限车辆，未超限部分按普通车辆合法装载收费标准记收，超限部分按基本费率计收
							finalFare = getRate(legalWeight, axletreeNo)
									* legalWeight * distance + baseRate
									* (weight - legalWeight) * distance;
						}
					}

				} else if (axletreeNo >= 6) {
					// 合法装载重量
					legalWeight = 49;
					if (weight < 0 || weight > 9999) {
						// System.out.println("weight is illegal");
					} else {
						if (weight <= 5) {
							weight = 5;
							finalFare = baseRate * weight * distance
									* favorableRate;
						} else if (weight > 5 && weight <= 35) {
							// 未超限车辆，按正常车辆收费标准的75%计收；
							changeRate = getRate(weight, axletreeNo);
							finalFare = changeRate * weight * distance
									* favorableRate;

						} else if (weight > 35 && weight <= legalWeight) {
							// 未超限车辆，按正常车辆收费标准的75%计收；
							changeRate = getRate(weight, axletreeNo);
							finalFare = changeRate * weight * distance
									* favorableRate;
						} else if (weight > legalWeight) {
							// 超限车辆，未超限部分按普通车辆合法装载收费标准记收，超限部分按基本费率计收
							finalFare = getRate(legalWeight, axletreeNo)
									* legalWeight * distance + baseRate
									* (weight - legalWeight) * distance;

						}
					}
				}
			} else if (type[1] == 1) {
				// 运输油汽等化学危险品标准罐车辆
				// 未超限车辆,按普通车辆收费标准记收
				// 超限车辆,未超限部分,按合法装载计费标准计收;超限0——50%（含50%）部分,按基本费率计收
				// (0.09元/吨公里),超限50%以上的车辆,按普通车辆收费标准计收

				if (axletreeNo == 2) {

					// 合法装载重量
					legalWeight = 17;
					if (weight < 0 || weight > 9999) {
						// System.out.println("weight is illegal");
					} else {
						if (weight <= 5) {
							weight = 5;
							finalFare = baseRate * weight * distance;
						} else if (weight > 5 && weight <= legalWeight) {
							// 未超限车辆,按普通车辆收费标准记收
							changeRate = getRate(weight, axletreeNo);
							finalFare = changeRate * weight * distance;
						} else if (weight > legalWeight
								&& weight <= legalWeight * 1.5) {

							// 超限车辆,未超限部分,按合法装载计费标准计收;超限0——50%（含50%）部分,按基本费率计收
							// (0.09元/吨公里)
							finalFare = baseRate * weight * distance;
						} else if (weight > legalWeight * 1.5
								&& weight <= legalWeight * 2) {

							// 超限50%以上的车辆,按普通车辆收费标准计收
							changeRate = getRate(weight, axletreeNo);

							finalFare = getRate(legalWeight, axletreeNo)
									* legalWeight * distance + changeRate
									* (weight - legalWeight) * distance;

						} else if (weight > legalWeight * 2) {

							// 超限50%以上的车辆,按普通车辆收费标准计收
							finalFare = getRate(legalWeight, axletreeNo)
									* legalWeight * distance + 6 * baseRate
									* (weight - legalWeight) * distance;
						}

					}

				} else if (axletreeNo == 3) {
					// 合法装载重量
					legalWeight = 27;
					if (weight < 0 || weight > 9999) {
						// System.out.println("weight is illegal");
					} else {
						if (weight <= 5) {
							weight = 5;
							finalFare = baseRate * weight * distance;
						} else if (weight > 5 && weight < 17) {
							// 未超限车辆,按普通车辆收费标准记收
							changeRate = getRate(weight, axletreeNo);
							finalFare = changeRate * weight * distance;
						} else if (weight >= 17 && weight <= legalWeight) {
							// 未超限车辆,按普通车辆收费标准记收
							changeRate = getRate(weight, axletreeNo);
							finalFare = changeRate * weight * distance;

						} else if (weight > legalWeight
								&& weight <= legalWeight * 1.5) {
							// 超限车辆,未超限部分,按合法装载计费标准计收;超限0——50%（含50%）部分,按基本费率计收
							// (0.09元/吨公里)
							finalFare = baseRate * weight * distance;

						} else if (weight > legalWeight * 1.5
								&& weight <= legalWeight * 2) {
							// 超限50%以上的车辆,按普通车辆收费标准计收
							changeRate = getRate(weight, axletreeNo);
							finalFare = getRate(legalWeight, axletreeNo)
									* legalWeight * distance + changeRate
									* (weight - legalWeight) * distance;

						} else if (weight > legalWeight * 2) {
							// 超限50%以上的车辆,按普通车辆收费标准计收

							finalFare = getRate(legalWeight, axletreeNo)
									* legalWeight * distance + 6 * baseRate
									* (weight - legalWeight) * distance;
						}

					}

				} else if (axletreeNo == 4) {
					// 合法装载重量
					legalWeight = 35;
					if (weight < 0 || weight > 9999) {
						// System.out.println("weight is illegal");
					} else {
						if (weight <= 5) {
							weight = 5;
							finalFare = baseRate * weight * distance;
						} else if (weight > 5 && weight <= legalWeight) {
							// 未超限车辆,按普通车辆收费标准记收
							changeRate = getRate(weight, axletreeNo);
							finalFare = changeRate * weight * distance;

						} else if (weight > legalWeight
								&& weight <= legalWeight * 1.5) {
							// 超限车辆,未超限部分,按合法装载计费标准计收;超限0——50%（含50%）部分,按基本费率计收
							// (0.09元/吨公里)
							finalFare = baseRate * weight * distance;

						} else if (weight > legalWeight * 1.5
								&& weight <= legalWeight * 2) {
							// 超限50%以上的车辆,按普通车辆收费标准计收
							changeRate = getRate(weight, axletreeNo);

							finalFare = getRate(legalWeight, axletreeNo)
									* legalWeight * distance + changeRate
									* (weight - legalWeight) * distance;

						} else if (weight > legalWeight * 2) {
							// 超限50%以上的车辆,按普通车辆收费标准计收

							finalFare = getRate(legalWeight, axletreeNo)
									* legalWeight * distance + 6 * baseRate
									* (weight - legalWeight) * distance;
						}

					}

				} else if (axletreeNo == 5) {
					// 合法装载重量
					legalWeight = 43;
					if (weight < 0 || weight > 9999) {
						// System.out.println("weight is illegal");
					} else {
						if (weight <= 5) {
							weight = 5;
							finalFare = baseRate * weight * distance;
						} else if (weight > 5 && weight <= 35) {
							// 未超限车辆,按普通车辆收费标准记收
							changeRate = getRate(weight, axletreeNo);
							finalFare = changeRate * weight * distance;

						} else if (weight > 35 && weight <= legalWeight) {
							// 未超限车辆,按普通车辆收费标准记收
							changeRate = getRate(weight, axletreeNo);
							finalFare = changeRate * weight * distance;

						} else if (weight > legalWeight
								&& weight <= legalWeight * 1.5) {
							// 超限车辆,未超限部分,按合法装载计费标准计收;超限0——50%（含50%）部分,按基本费率计收
							// (0.09元/吨公里)
							finalFare = baseRate * weight * distance;

						} else if (weight > legalWeight * 1.5
								&& weight <= legalWeight * 2) {
							// 超限50%以上的车辆,按普通车辆收费标准计收
							changeRate = getRate(weight, axletreeNo);

							finalFare = getRate(legalWeight, axletreeNo)
									* legalWeight * distance + changeRate
									* (weight - legalWeight) * distance;

						} else if (weight > legalWeight * 2) {
							// 超限50%以上的车辆,按普通车辆收费标准计收

							finalFare = getRate(legalWeight, axletreeNo)
									* legalWeight * distance + 6 * baseRate
									* (weight - legalWeight) * distance;
						}

					}

				} else if (axletreeNo >= 6) {
					// 合法装载重量
					legalWeight = 49;
					if (weight < 0 || weight > 9999) {
						// System.out.println("weight is illegal");
					} else {
						if (weight <= 5) {
							weight = 5;
							finalFare = baseRate * weight * distance;
						} else if (weight > 5 && weight <= 35) {
							// 未超限车辆,按普通车辆收费标准记收
							changeRate = getRate(weight, axletreeNo);
							finalFare = changeRate * weight * distance;

						} else if (weight > 35 && weight <= legalWeight) {
							// 未超限车辆,按普通车辆收费标准记收
							changeRate = getRate(weight, axletreeNo);
							finalFare = changeRate * weight * distance;

						} else if (weight > legalWeight
								&& weight <= legalWeight * 1.5) {
							// 超限车辆,未超限部分,按合法装载计费标准计收;超限0——50%（含50%）部分,按基本费率计收
							// (0.09元/吨公里)
							finalFare = baseRate * weight * distance;

						} else if (weight > legalWeight * 1.5
								&& weight <= legalWeight * 2) {

							// 超限50%以上的车辆,按普通车辆收费标准计收
							changeRate = getRate(weight, axletreeNo);

							finalFare = getRate(legalWeight, axletreeNo)
									* legalWeight * distance + changeRate
									* (weight - legalWeight) * distance;

						} else if (weight > legalWeight * 2) {
							// 超限50%以上的车辆,按普通车辆收费标准计收

							finalFare = getRate(legalWeight, axletreeNo)
									* legalWeight * distance + 6 * baseRate
									* (weight - legalWeight) * distance;
						}

					}

				}
			} else if (type[1] == 2) {
				// 运输国际标准集装箱车辆
				// 未超过优惠限值标准的车辆，按普通车辆收费标准的85%计收；
				// 超过优惠限值标准的车辆，按普通车辆收费标准计收

				// 优惠比率
				double favorableRate = 0.85;
				if (type[2] == 0) {
					// 四轴车辆运输20英尺集装箱37吨
					// 优惠装载重量
					legalWeight = 37;
					// 普通车辆合法装载重量
					double legalWeight_old = 35;
					if (weight < 0 || weight > 9999) {
						// System.out.println("weight is illegal");
					} else {
						if (weight <= 5) {
							weight = 5;
							finalFare = baseRate * weight * distance
									* favorableRate;
						} else if (weight > 5 && weight <= legalWeight) {
							// 未超过优惠限值标准的车辆，按普通车辆收费标准的85%计收；
							changeRate = getRate(weight, axletreeNo);
							finalFare = changeRate * weight * distance
									* favorableRate;
						} else if (weight > legalWeight
								&& weight <= legalWeight_old * 1.3) {
							// 超过优惠限值标准的车辆，按普通车辆收费标准计收
							finalFare = getRate(legalWeight_old, axletreeNo)
									* legalWeight_old * distance + baseRate
									* (weight - legalWeight_old) * distance;

						} else if (weight > legalWeight_old * 1.3
								&& weight <= legalWeight_old * 2) {
							// 超过优惠限值标准的车辆，按普通车辆收费标准计收
							changeRate = getRate(weight, axletreeNo);

							finalFare = getRate(legalWeight_old, axletreeNo)
									* legalWeight_old * distance + changeRate
									* (weight - legalWeight_old) * distance;

						} else if (weight > legalWeight_old * 2) {
							// 超过优惠限值标准的车辆，按普通车辆收费标准计收
							finalFare = getRate(legalWeight_old, axletreeNo)
									* legalWeight_old * distance + 6 * baseRate
									* (weight - legalWeight_old) * distance;
						}

					}

				} else if (type[2] == 1) {
					// 四轴车辆运输40英尺集装箱44吨
					// 集装箱运输车优惠重量
					legalWeight = 44;
					// 普通车辆合法装载重量
					double legalWeight_old = 35;
					if (weight < 0 || weight > 9999) {
						// System.out.println("weight is illegal");
					} else {
						if (weight <= 5) {
							weight = 5;
							finalFare = baseRate * weight * distance
									* favorableRate;
						} else if (weight > 5 && weight <= legalWeight) {
							// 未超过优惠限值标准的车辆，按普通车辆收费标准的85%计收；
							changeRate = getRate(weight, axletreeNo);
							finalFare = changeRate * weight * distance
									* favorableRate;
						} else if (weight > legalWeight
								&& weight <= legalWeight_old * 1.3) {
							// 超过优惠限值标准的车辆，按普通车辆收费标准计收
							finalFare = getRate(legalWeight_old, axletreeNo)
									* legalWeight_old * distance + baseRate
									* (weight - legalWeight_old) * distance;

						} else if (weight > legalWeight_old * 1.3
								&& weight <= legalWeight_old * 2) {
							// 超过优惠限值标准的车辆，按普通车辆收费标准计收
							changeRate = getRate(weight, axletreeNo);

							finalFare = getRate(legalWeight_old, axletreeNo)
									* legalWeight_old * distance + changeRate
									* (weight - legalWeight_old) * distance;

						} else if (weight > legalWeight_old * 2) {
							// 超过优惠限值标准的车辆，按普通车辆收费标准计收
							finalFare = getRate(legalWeight_old, axletreeNo)
									* legalWeight_old * distance + 6 * baseRate
									* (weight - legalWeight_old) * distance;
						}

					}

				} else if (type[2] == 2) {
					// 五轴车辆运输40英尺集装箱46吨
					// 集装箱运输车优惠重量
					legalWeight = 46;
					// 普通车辆合法装载重量
					double legalWeight_old = 43;
					if (weight < 0 || weight > 9999) {
						// System.out.println("weight is illegal");
					} else {
						if (weight <= 5) {
							weight = 5;
							finalFare = baseRate * weight * distance
									* favorableRate;
						} else if (weight > 5 && weight <= 35) {
							// 未超过优惠限值标准的车辆，按普通车辆收费标准的85%计收；
							changeRate = getRate(weight, axletreeNo);
							finalFare = changeRate * weight * distance
									* favorableRate;

						} else if (weight > 35 && weight <= legalWeight_old) {
							// 未超过优惠限值标准的车辆，按普通车辆收费标准的85%计收；
							changeRate = getRate(weight, axletreeNo);
							finalFare = changeRate * weight * distance
									* favorableRate;

						} else if (weight > legalWeight_old
								&& weight <= legalWeight) {
							// 未超过优惠限值标准的车辆，按普通车辆收费标准的85%计收；
							finalFare = (getRate(legalWeight, axletreeNo)
									* legalWeight * distance + baseRate
									* (weight - legalWeight) * distance)
									* favorableRate;

						} else if (weight > legalWeight
								&& weight <= legalWeight_old * 1.3) {
							// 超过优惠限值标准的车辆，按普通车辆收费标准计收
							finalFare = getRate(legalWeight_old, axletreeNo)
									* legalWeight_old * distance + baseRate
									* (weight - legalWeight_old) * distance;

						} else if (weight > legalWeight_old * 1.3
								&& weight <= legalWeight_old * 2) {
							// 超过优惠限值标准的车辆，按普通车辆收费标准计收
							changeRate = getRate(weight, axletreeNo);

							finalFare = getRate(legalWeight_old, axletreeNo)
									* legalWeight_old * distance + changeRate
									* (weight - legalWeight_old) * distance;

						} else if (weight > legalWeight_old * 2) {
							// 超过优惠限值标准的车辆，按普通车辆收费标准计收
							finalFare = getRate(legalWeight_old, axletreeNo)
									* legalWeight_old * distance + 6 * baseRate
									* (weight - legalWeight_old) * distance;
						}

					}

				} else if (type[2] == 3) {
					// 五轴车辆运输2个20英尺集装箱63吨
					// 集装箱运输车优惠重量
					legalWeight = 63;
					// 普通车辆合法装载重量
					double legalWeight_old = 43;
					if (weight < 0 || weight > 9999) {
						// System.out.println("weight is illegal");
					} else {
						if (weight <= 5) {
							weight = 5;
							finalFare = baseRate * weight * distance
									* favorableRate;
						} else if (weight > 5 && weight <= 35) {
							// 未超过优惠限值标准的车辆，按普通车辆收费标准的85%计收；
							changeRate = getRate(weight, axletreeNo);
							finalFare = changeRate * weight * distance
									* favorableRate;

						} else if (weight > 35 && weight <= legalWeight_old) {
							// 未超过优惠限值标准的车辆，按普通车辆收费标准的85%计收；
							changeRate = getRate(weight, axletreeNo);
							finalFare = changeRate * weight * distance
									* favorableRate;

						} else if (weight > legalWeight_old
								&& weight <= legalWeight_old * 1.3) {
							// 未超过优惠限值标准的车辆，按普通车辆收费标准的85%计收；
							finalFare = (getRate(legalWeight_old, axletreeNo)
									* legalWeight_old * distance + baseRate
									* (weight - legalWeight_old) * distance)
									* favorableRate;

						} else if (weight > legalWeight_old * 1.3
								&& weight <= legalWeight) {
							// 未超过优惠限值标准的车辆，按普通车辆收费标准的85%计收；
							changeRate = getRate(weight, axletreeNo);

							finalFare = (getRate(legalWeight_old, axletreeNo)
									* legalWeight_old * distance + changeRate
									* (weight - legalWeight_old) * distance)
									* favorableRate;

						}

						else if (weight > legalWeight
								&& weight <= legalWeight_old * 2) {
							// 超过优惠限值标准的车辆，按普通车辆收费标准计收
							changeRate = getRate(weight, axletreeNo);

							finalFare = getRate(legalWeight_old, axletreeNo)
									* legalWeight_old * distance + changeRate
									* (weight - legalWeight_old) * distance;

						} else if (weight > legalWeight_old * 2) {
							// 超过优惠限值标准的车辆，按普通车辆收费标准计收

							finalFare = getRate(legalWeight_old, axletreeNo)
									* legalWeight_old * distance + 6 * baseRate
									* (weight - legalWeight_old) * distance;
						}

					}

				} else if (type[2] == 4) {
					// 六轴车辆运输40英尺集装箱48吨
					// 集装箱运输车优惠重量
					legalWeight = 48;
					// 普通车辆合法装载重量
					double legalWeight_old = 49;
					if (weight < 0 || weight > 9999) {
						// System.out.println("weight is illegal");
					} else {
						if (weight <= 5) {
							weight = 5;
							finalFare = baseRate * weight * distance
									* favorableRate;
						} else if (weight > 5 && weight <= 35) {
							// 未超过优惠限值标准的车辆，按普通车辆收费标准的85%计收；
							changeRate = getRate(weight, axletreeNo);
							finalFare = changeRate * weight * distance
									* favorableRate;

						} else if (weight > 35 && weight <= legalWeight) {
							// 未超过优惠限值标准的车辆，按普通车辆收费标准的85%计收；
							changeRate = getRate(weight, axletreeNo);
							finalFare = changeRate * weight * distance
									* favorableRate;

						} else if (weight > legalWeight
								&& weight <= legalWeight_old) {
							// 超过优惠限值标准的车辆，按普通车辆收费标准计收
							changeRate = getRate(weight, axletreeNo);
							finalFare = changeRate * weight * distance;

						} else if (weight > legalWeight_old
								&& weight <= legalWeight_old * 1.3) {
							// 超过优惠限值标准的车辆，按普通车辆收费标准计收
							finalFare = getRate(legalWeight_old, axletreeNo)
									* legalWeight_old * distance + baseRate
									* (weight - legalWeight_old) * distance;

						} else if (weight > legalWeight_old * 1.3
								&& weight <= legalWeight_old * 2) {
							// 超过优惠限值标准的车辆，按普通车辆收费标准计收
							changeRate = getRate(weight, axletreeNo);

							finalFare = getRate(legalWeight_old, axletreeNo)
									* legalWeight_old * distance + changeRate
									* (weight - legalWeight_old) * distance;

						} else if (weight > legalWeight_old * 2) {
							// 超过优惠限值标准的车辆，按普通车辆收费标准计收
							finalFare = getRate(legalWeight_old, axletreeNo)
									* legalWeight_old * distance + 6 * baseRate
									* (weight - legalWeight_old) * distance;
						}

					}

				}

				else if (type[2] == 5) {
					// 六轴车辆运输2个20英尺集装箱65吨
					// 集装箱运输车优惠重量
					legalWeight = 65;
					// 普通车辆合法装载重量
					double legalWeight_old = 49;
					if (weight < 0 || weight > 9999) {
						// System.out.println("weight is illegal");
					} else {
						if (weight <= 5) {
							weight = 5;
							finalFare = baseRate * weight * distance
									* favorableRate;
						} else if (weight > 5 && weight <= 35) {
							// 未超过优惠限值标准的车辆，按普通车辆收费标准的85%计收；
							changeRate = getRate(weight, axletreeNo);// ���÷��ʺ�����������
							finalFare = changeRate * weight * distance
									* favorableRate;

						} else if (weight > 35 && weight <= legalWeight_old) {
							// 未超过优惠限值标准的车辆，按普通车辆收费标准的85%计收；
							changeRate = getRate(weight, axletreeNo);
							finalFare = changeRate * weight * distance
									* favorableRate;

						} else if (weight > legalWeight_old
								&& weight <= legalWeight_old * 1.3) {
							// 未超过优惠限值标准的车辆，按普通车辆收费标准的85%计收；
							finalFare = (getRate(legalWeight_old, axletreeNo)
									* legalWeight_old * distance + baseRate
									* (weight - legalWeight_old) * distance)
									* favorableRate;

						} else if (weight > legalWeight_old * 1.3
								&& weight <= legalWeight) {
							// 未超过优惠限值标准的车辆，按普通车辆收费标准的85%计收；
							changeRate = getRate(weight, axletreeNo);

							finalFare = (getRate(legalWeight_old, axletreeNo)
									* legalWeight_old * distance + changeRate
									* (weight - legalWeight_old) * distance)
									* favorableRate;

						}

						else if (weight > legalWeight
								&& weight <= legalWeight_old * 2) {
							// 超过优惠限值标准的车辆，按普通车辆收费标准计收
							changeRate = getRate(weight, axletreeNo);

							finalFare = getRate(legalWeight_old, axletreeNo)
									* legalWeight_old * distance + changeRate
									* (weight - legalWeight_old) * distance;

						} else if (weight > legalWeight_old * 2) {
							// 超过优惠限值标准的车辆，按普通车辆收费标准计收

							finalFare = getRate(legalWeight_old, axletreeNo)
									* legalWeight_old * distance + 6 * baseRate
									* (weight - legalWeight_old) * distance;
						}

					}

				}

			}

		}

		return finalFare;
	}

	// 获取费率的函数
	public static double getRate(double weight, int axletreeNo) {

		double baseRate = 0.09;
		if (axletreeNo == 2) {
			double legalWeight = 17;
			if (weight <= 5) {
				return baseRate;
			} else if (weight > 5 && weight < legalWeight) {
				// System.out.println("基本费率0.09元/吨公里线性递减至0.066元/吨公里");
				return (-0.0014 * weight + 0.0969);
			} else if (weight == legalWeight) {
				// System.out.println("基本费率0.09元/吨公里线性递减至0.0486元/吨公里");
				return (-0.0014 * weight + 0.0969);
			} else if (weight > legalWeight && weight <= legalWeight * 1.3) {
				return baseRate;
			} else if (weight > legalWeight * 1.3 && weight <= legalWeight * 2) {
				// System.out.println("按基本费率的3倍线性递增至6倍计收");
				return (-0.0014 * weight + 0.0969);
			} else if (weight > legalWeight * 2) {
				return baseRate * 6;
			}
		} else if (axletreeNo == 3) {
			double legalWeight = 27;
			if (weight <= 5) {
				return baseRate;
			} else if (weight > 5 && weight < 17) {
				// System.out.println("基本费率0.09元/吨公里线性递减至0.066元/吨公里");
				return (-0.0014 * weight + 0.0969);
			} else if (weight >= 17 && weight <= legalWeight) {
				// System.out.println("基本费率0.09元/吨公里线性递减至0.0486元/吨公里");
				return (-0.0014 * weight + 0.0969);
			} else if (weight > legalWeight && weight <= legalWeight * 1.3) {
				return baseRate;
			} else if (weight > legalWeight * 1.3 && weight <= legalWeight * 2) {
				// System.out.println("按基本费率的3倍线性递增至6倍计收");
				return (-0.0014 * weight + 0.0969);
			} else if (weight > legalWeight * 2) {
				return baseRate * 6;
			}

		} else if (axletreeNo == 4) {
			double legalWeight = 35;
			if (weight <= 5) {
				return baseRate;
			} else if (weight > 5 && weight <= legalWeight) {
				// System.out.println("基本费率0.09元/吨公里线性递减至0.0486元/吨公里");
				return (-0.0014 * weight + 0.0969);
			} else if (weight > legalWeight && weight <= legalWeight * 1.3) {
				// System.out.println("0.0486元/吨公里非线性递减至0.0346元/吨公里");
				return baseRate;
			} else if (weight > legalWeight * 1.3 && weight <= legalWeight * 2) {
				// System.out.println("按基本费率的3倍线性递增至6倍计收");
				return (-0.0014 * weight + 0.0969);
			} else if (weight > legalWeight * 2) {
				return baseRate * 6;
			}

		} else if (axletreeNo == 5) {
			double legalWeight = 43;
			if (weight <= 5) {
				return baseRate;
			} else if (weight > 5 && weight <= 35) {
				// System.out.println("基本费率0.09元/吨公里线性递减至0.0486元/吨公里");
				return (-0.0014 * weight + 0.0969);
			} else if (weight > 35 && weight <= legalWeight) {
				// System.out.println("0.0486元/吨公里非线性递减至0.0346元/吨公里");
				return baseRate;
			} else if (weight > legalWeight && weight <= legalWeight * 1.3) {
				// System.out.println("按基本费率的3倍线性递增至6倍计收");
				return baseRate;
			} else if (weight > legalWeight * 1.3 && weight <= legalWeight * 2) {
				// System.out.println("按基本费率的3倍线性递增至6倍计收");
				return (-0.0014 * weight + 0.0969);
			} else if (weight > legalWeight * 2) {
				return 6 * baseRate;
			}

		} else if (axletreeNo >= 6) {
			double legalWeight = 49;
			if (weight <= 5) {
				return baseRate;
			} else if (weight > 5 && weight <= 35) {
				// System.out.println("基本费率0.09元/吨公里线性递减至0.0486元/吨公里");
				return (-0.0014 * weight + 0.0969);
			} else if (weight > 35 && weight <= legalWeight) {
				// System.out.println("0.0486元/吨公里非线性递减至0.0346元/吨公里");
				return baseRate;
			} else if (weight > legalWeight && weight <= legalWeight * 1.3) {
				// System.out.println("按基本费率的3倍线性递增至6倍计收");
				return baseRate;
			} else if (weight > legalWeight * 1.3 && weight <= legalWeight * 2) {
				// System.out.println("按基本费率的3倍线性递增至6倍计收");
				return (-0.0014 * weight + 0.0969);
			} else if (weight > legalWeight * 2) {
				return 6 * baseRate;
			}
		}
		return baseRate;

	}

	// 载重舍入处理，获取重量的函数
	public static double getWeight(double weight) {
		double finalWeight;
		int intWeight = (int) (weight * 10);
		int tempWeight = (int) (weight * 100);
		if (tempWeight % 10 >= 5) {
			intWeight = intWeight + 1;
		}
		finalWeight = intWeight / 10.0;
		return finalWeight;

	}

	// 通行费舍入处理， 获取费用的函数
	public static int countCarFareExpress(double fare) {

		double remainder = (fare) % 10;
		double tempFare = (fare - remainder);
		double finalFare = tempFare;
		if (remainder < 2.5) {
			finalFare = tempFare;
		} else if (remainder >= 7.5) {
			finalFare = tempFare + 10;
		} else {

			finalFare = tempFare + 5;
		}
		if (finalFare <= 5) {
			finalFare = 5;
		}

		return (int) finalFare;
	}

}
