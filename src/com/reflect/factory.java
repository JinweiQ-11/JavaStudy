package com.reflect;

    interface IFood { 									// 定义食物标准
        public void eat(); 								// 食物的核心功能：吃
    }
    class Bread implements IFood { 						// 食物：面包
        public void eat() {								// 覆写方法
            System.out.println("吃面包。");
        }
    }
    class Milk implements IFood { 						// 食物：牛奶
        public void eat() {								// 覆写方法
            System.out.println("喝牛奶。");
        }
    }
    class FFactory {
        /**
         * 获取IFood接口实例化对象，利用此方法对外隐藏子类，由于Factory类没有属性，所以定义static方法
         * @param className 要获取的子类标记
         * @return 存在指定标记返回对应子类实例，否则返回null
         */
        public static IFood getInstance(String className) {
            if ("bread".equals(className)) {				// 判断子类标记
                return new Bread();						// 返回子类实例
            } else if ("milk".equals(className)) {			// 判断子类标记
                return new Milk();						// 返回子类实例
            } else {
                return null;								// 没有匹配类型返回null
            }
        }
    }
    public class factory {
        public static void main(String args[]) {
            IFood food = FFactory.getInstance("bread");		// 通过工厂获取实例
            food.eat();									// 调用公共标准
        }
    }
