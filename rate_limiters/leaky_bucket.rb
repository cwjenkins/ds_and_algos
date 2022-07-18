class LeakyBucket
  attr_accessor :capacity, :drops, :drip_rate, :last_request

  # capacity: how many requests can be handled within an interval
  # time_to_drain: how many seconds it takes to drain the bucket
  def initialize(capacity, time_to_drain)
    self.capacity     = capacity
    self.drops        = 0
    self.drip_rate    = (time_to_drain * 1000) / capacity # use millisecond precision
    self.last_request = current_time
  end

  def time_to_wait
    ct = current_time

    # Calculate drips from last request
    if drops > 0
      @drops -= ((ct - last_request) / drip_rate).floor
      @drops = [drops, 0].max
    end

    # Determine if we have capacity
    if drops < capacity
      @drops += 1
      delay = (drops - 1) * drip_rate
      @last_request = ct

      return delay
    end
  end

  private

  # current time in milliseconds
  def current_time
    Time.now.to_f * 1000
  end
end
