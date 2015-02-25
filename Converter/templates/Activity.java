
	@Override public void onModuleLoad() {
    	CoreInitilizer.init();
    	super.onModuleLoad();
    }
    
    @Override public void onModuleLoaded() {
    	CoreInitilizer.initLayout();
    }
    
    